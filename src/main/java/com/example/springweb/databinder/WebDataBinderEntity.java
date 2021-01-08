package com.example.springweb.databinder;

import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2021-01-08 14:51:00
 * @description
 */
public class WebDataBinderEntity extends DataBinderEntity {
    private static final long serialVersionUID = 5536832871463793830L;
    private String[] stringArrays;
    private Collection<Integer> integerCollection;
    private List<String> stringList;
    private Map<String, Object> objectMap;
    private MultipartFile singleFile;
    private MultipartFile[] multipartFileArray;
    private List<MultipartFile> multipartFileList;

    public String[] getStringArrays() {
        return stringArrays;
    }

    public void setStringArrays(String[] stringArrays) {
        this.stringArrays = stringArrays;
    }

    public Collection<Integer> getIntegerCollection() {
        return integerCollection;
    }

    public void setIntegerCollection(Collection<Integer> integerCollection) {
        this.integerCollection = integerCollection;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public Map<String, Object> getObjectMap() {
        return objectMap;
    }

    public void setObjectMap(Map<String, Object> objectMap) {
        this.objectMap = objectMap;
    }

    public MultipartFile getSingleFile() {
        return singleFile;
    }

    public void setSingleFile(MultipartFile singleFile) {
        this.singleFile = singleFile;
    }

    public MultipartFile[] getMultipartFileArray() {
        return multipartFileArray;
    }

    public void setMultipartFileArray(MultipartFile[] multipartFileArray) {
        this.multipartFileArray = multipartFileArray;
    }

    public List<MultipartFile> getMultipartFileList() {
        return multipartFileList;
    }

    public void setMultipartFileList(List<MultipartFile> multipartFileList) {
        this.multipartFileList = multipartFileList;
    }
}
