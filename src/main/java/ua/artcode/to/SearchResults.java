package ua.artcode.to;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serhii on 28.05.16.
 */
public class SearchResults<E> {

    private String elementsType;
    private List<E> list;

    public SearchResults() {
        list = new ArrayList<>();
    }

    public SearchResults(String elementsType, List<E> list) {
        this.elementsType = elementsType;
        this.list = list;
    }

    public String getElementsType() {
        return elementsType;
    }

    public void setElementsType(String elementsType) {
        this.elementsType = elementsType;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }
}
