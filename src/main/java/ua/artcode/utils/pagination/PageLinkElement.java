package ua.artcode.utils.pagination;

/**
 * Created by serhii on 24.05.16.
 */
public class PageLinkElement {

    private long pageNum;
    private long offset;
    private long length;
    private boolean active;

    public PageLinkElement() {
    }

    public PageLinkElement(long pageNum, long offset, long length, boolean active) {
        this.pageNum = pageNum;
        this.offset = offset;
        this.length = length;
        this.active = active;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public long getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isActive() {
        return active;
    }

    public void setIsActive(boolean isActive) {
        this.active = isActive;
    }
}
