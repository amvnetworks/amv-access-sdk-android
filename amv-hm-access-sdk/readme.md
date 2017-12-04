amv-hm-access-sdk
=================

### sdk usage
#### initialization
After an instance of `AccessSdk` is created it must be initialized:
```java
AccessSdk accessSdk = // ...

accessSdk.initialize()
  .subscribe(success -> {
    Log.d("MyApp", "Successfully initialized");
  }, e -> {
    Log.e("MyApp", "Error while initializing: " + e.getMessage(), e);
  });
```

`initialize` will check if a device certificate + associated key pair exists locally and
requests one from a specified remote server if none can be found. This means that your application
needs permission to access the internet:
```xml
  <uses-permission android:name="android.permission.INTERNET" />
```

After a device certificate is present the sdk is initialized and ready to be used.

#### certificate management
In order to interact with certificates you must get an `CertificateManager` instance.
```java
AccessSdk accessSdk = // ...

CertificateManager certificateManager = accessSdk.certificateManager();

// ...
```
##### device certificate
```java
certificateManager.getDeviceCertificate()
  .subscribe(deviceCertificate -> {
    Log.i("MyApp", "Your device serial number is " + deviceCertificate.getDeviceSerial());
  }, e -> {
    Log.e("MyApp", "Error while getting device certificate: " + e.getMessage(), e);
  });
```

##### access certificates
```java
certificateManager.getAccessCertificates()
  .toList()
  .subscribe(accessCertificates -> {
    Log.i("MyApp", String.format("You currently have %s access certificates on your device.",
      accessCertificates.size()));
  }, e -> {
    Log.e("MyApp", "Error while getting access certificates: " + e.getMessage(), e);
  });
```

###### refresh access certificates
```java
certificateManager.refreshAccessCertificates()
  .toList()
  .subscribe(accessCertificates -> {
    Log.i("MyApp", String.format("Successfully downloaded %s access certificates.",
      accessCertificates.size()));
  }, e -> {
    Log.e("MyApp", "Error while refreshing access certificates: " + e.getMessage(), e);
  });
```

###### revoke an access certificate
```java
AccessCertificatePair certificatePair = // ...
certificateManager.revokeAccessCertificate(certificatePair)
  .subscribe(success -> {
    Log.i("MyApp", String.format("Successfully revoked certificate '%s'.",
      certificatePair.getId()));
  }, e -> {
    Log.e("MyApp", "Error while revoking certificate: " + e.getMessage(), e);
  });
```

### sample app usage
#### initialization
`AmvSdkInitializer.create(Context context)` will create and initialize an `AccessSdk` instance.
It does so by reading api credentials from a the `application.properties` file (as mentioned above
this is done for demonstration purposes only and imposes a security risk).

#### certificate management
To handle certificates your Activity should implement `ICertificatesView` interface for callbacks.
Main access point for is in `CertificateController.java`. You can initialize it in your
Activity with:
```java
this.controller = new CertificateController();
this.controller.initialize(this, this);
```

If initialize succeeds then ICertificatesView's `onInitializeFinished()` is called.
After this you can call the CertificateController's
```java
void downloadCertificates();
```
to download/refresh the access certificates. When finished, ICertificatesView's
`onCertificatesDownloaded()` will be called.

Also,
```java
Observable<AccessCertificatePair> getCertificates();
```
is available to get the already downloaded access certificates. These certificates will be used
to start bluetooth broadcasting and you can use the gaining serial in them to get the vehicle serial.

To revoke a certificate from the server
```java
void revokeCertificate(AccessCertificate certificate);
```
can be called. If the backend server deletes the certificate successfully the locally stored copy
will also be removed. Subsequently `onCertificateRevoked();` is called.

Once access certificates are present, they can be displayed to the user. When the user selects a
certificate, bluetooth broadcasting will be started and the app tries to connect to the vehicle.