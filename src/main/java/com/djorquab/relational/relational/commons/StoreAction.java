package com.djorquab.relational.relational.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreAction<E> implements Serializable {
    private StorageType type;
    private JMSOperation operation;
    private E payload;
}
