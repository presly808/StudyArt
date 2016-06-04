package ua.artcode.utils.pagination;

/**
 * Created by serhii on 24.05.16.
 */
public class PageLinkElement {

    private long pageNum;
    private long offset;
    private long length;
    private boolean highlighted;
    private boolean disabled;

    public PageLinkElement() {
    }

    public PageLinkElement(long pageNum, long offset, long length, boolean highlighted) {
        this.pageNum = pageNum;
        this.offset = offset;
        this.length = length;
        this.highlighted = highlighted;
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

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setIsActive(boolean isActive) {
        this.highlighted = isActive;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
