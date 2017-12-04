package org.amv.access.sdk.spi.communication.impl;

import org.amv.access.sdk.spi.communication.Command;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class SimpleType implements Command.Type {
    private String id;
}
