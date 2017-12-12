package org.amv.access.sdk.spi.communication;

public interface CommunicationManagerFactory<T extends CommunicationManager> {

    T createCommunicationManager();
}
