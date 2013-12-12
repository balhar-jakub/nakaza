package org.pilirion.nakaza.api;

import java.io.Serializable;

/**
 * Every entity must have a way to identify it uniquely. Usually it is going to be Long id. But it may be something
 * else as long as it is serializable.
 */
public interface Identifiable<T extends Serializable>
{
    T getId();
}