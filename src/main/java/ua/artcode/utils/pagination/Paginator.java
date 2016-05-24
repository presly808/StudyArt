package ua.artcode.utils.pagination;

import java.util.ArrayList;
import java.util.List;

public class Paginator {

    // todo simplify logic of pagination
    public static List<PageLinkElement> getPaginationElements(long offset, long limit, long total){

        List<PageLinkElement> pageLinks = new ArrayList<>();

        long length = offset + limit;

        if(offset == 0 && length >= total){
            pageLinks.add(new PageLinkElement(0, offset, total, true));
            return pageLinks;
        }

        if(length >= total){
            offset = total - limit;
        }

        long step = limit;

        long leftStepLimit = offset - (step * 3);
        long leftFromCurr = leftStepLimit > 0 ? leftStepLimit : offset;

        //first pages
        long rightFromStart = step * 2;
        for (long i = 0; i < rightFromStart && i < leftFromCurr; i += step) {
            pageLinks.add(new PageLinkElement(0, i, i + step, false));
        }

        if(rightFromStart <= leftFromCurr){
            pageLinks.add(new PageLinkElement(-1,0,0,false));
        }

        // left nearest pages from current
        for (long i = leftFromCurr + step; i < offset; i += step) {
            pageLinks.add(new PageLinkElement(0, i, i + step, false));
        }

        // current
        if(length >= total) {
            pageLinks.add(new PageLinkElement(0, offset, offset + step, true));
        }

        long rightStepLimit = offset + (step * 3);
        long rightFromCurr = rightStepLimit < total ? rightStepLimit : total;
        // right nearest pages from current
        for (long i = offset + step; i < rightFromCurr; i += step) {
            pageLinks.add(new PageLinkElement(0, i, i + step, false));
        }

        //first pages
        long leftFromEnd = total - step * 2;
        leftFromEnd = leftFromEnd > rightFromCurr ? leftFromEnd : rightFromCurr;

        if(leftFromEnd > rightFromCurr){
            pageLinks.add(new PageLinkElement(-1,0,0,false));
        }

        for (long i = leftFromEnd; i >= rightFromCurr && i < total; i += step) {
            if(i + step >= total){
                pageLinks.add(new PageLinkElement(0, i, i + (total - i), false));
            } else {
                pageLinks.add(new PageLinkElement(0, i, i + step, false));
            }
        }

        return pageLinks;
    }

    public static void main(String[] args) {

        System.out.println("500,100,889");
        List<PageLinkElement> pagesLink = getPaginationElements(500,100,889);

        pagesLink.stream().forEach((el) -> System.out.printf("%d-%d:", el.getOffset(), el.getLength()));

        System.out.println();
        System.out.println("0,50,1000");
        List<PageLinkElement> pagesLink2 = getPaginationElements(0,50,1000);

        pagesLink2.stream().forEach((el) -> System.out.printf("%d-%d:", el.getOffset(), el.getLength()));

        System.out.println();
        System.out.println("950,50,1000");
        List<PageLinkElement> pagesLink3 = getPaginationElements(950,50,1000);

        pagesLink3.stream().forEach((el) -> System.out.printf("%d-%d:", el.getOffset(), el.getLength()));

        System.out.println();
        System.out.println("1000,50,1000");
        List<PageLinkElement> pagesLink4 = getPaginationElements(1000,50,1000);

        pagesLink4.stream().forEach((el) -> System.out.printf("%d-%d:", el.getOffset(), el.getLength()));

        System.out.println();
        System.out.println("50,50,1000");
        List<PageLinkElement> pagesLink5 = getPaginationElements(50,50,1000);

        pagesLink5.stream().forEach((el) -> System.out.printf("%d-%d:", el.getOffset(), el.getLength()));

        System.out.println();
        System.out.println("100,50,144");
        List<PageLinkElement> pagesLink6 = getPaginationElements(100,50,144);

        pagesLink6.stream().forEach((el) -> System.out.printf("%d-%d:", el.getOffset(), el.getLength()));

        System.out.println();
        System.out.println("25,25,30");
        List<PageLinkElement> pagesLink7 = getPaginationElements(25,25,30);

        pagesLink7.stream().forEach((el) -> System.out.printf("%d-%d:", el.getOffset(), el.getLength()));

        System.out.println();
        List<PageLinkElement> pagesLink8 = getPaginationElements(0,25,7);
        System.out.println("0,25,7");
        pagesLink8.stream().forEach((el) -> System.out.printf("%d-%d:", el.getOffset(), el.getLength()));
    }

}
