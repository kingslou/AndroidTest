package com.loujin.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 实体List
 * @author jin
 *
 * @param <T>
 */
public interface ListEntity<T extends Entity> extends Serializable {

    public List<T> getList();
}
