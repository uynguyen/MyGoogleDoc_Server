/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EditorKits;

import javax.swing.SizeRequirements;
import javax.swing.text.Element;
import javax.swing.text.ParagraphView;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author Vin
 */
public class AdvancedHTMLEditorKit extends HTMLEditorKit {
//    PageableViewFactory factory = new PageableViewFactory();
//    protected int pageWidth = 150;
//    protected int pageHeight = 200;
//    public static int DRAW_PAGE_INSET = 15;
//    protected Insets pageMargins = new Insets(10, 10, 10, 10);
    public AdvancedHTMLEditorKit() {
        super();
    }

    @Override
    public ViewFactory getViewFactory() {
        return new HTMLFactory() {
            @Override
            public View create(Element e) {
                View v = super.create(e);              
                if (v instanceof ParagraphView) {
                    return new ParagraphView(e) {
                        @Override
                        protected SizeRequirements calculateMinorAxisRequirements(int axis, SizeRequirements r) {
                            if (r == null) {
                                r = new SizeRequirements();
                            }
                            float pref = layoutPool.getPreferredSpan(axis);
                            float min = layoutPool.getMinimumSpan(axis);
                            r.minimum = (int) min;
                            r.preferred = Math.max(r.minimum, (int) pref);
                            r.maximum = Integer.MAX_VALUE;
                            r.alignment = 0.5f;
                            return r;
                        }
                    };
                }
                return v;
            }
        };
    }
    
    
//     /**
//     * Sets page width
//     * @param width int width value in pixels
//     */
//    public void setPageWidth(int width) {
//        pageWidth = width;
//    }
//
//    /**
//     * gets page width
//     * @return int width in pixels
//     */
//    public int getPageWidth() {
//        return pageWidth;
//    }
//
//    /**
//     * Sets page height
//     * @param height int height in pixels
//     */
//    public void setPageHeight(int height) {
//        pageHeight = height;
//    }
//
//    /**
//     * Gets page height
//     * @return int height in pixels
//     */
//    public int getPageHeight() {
//        return pageHeight;
//    }
//
//    /**
//     * Sets page margins (distance between page content and page edge.
//     * @param margins Insets margins.
//     */
//    public void setPageMargins(Insets margins) {
//        pageMargins = margins;
//    }
//
//    
//    
//
//    /**
//     * The view factory class creates custom views for pagination
//     * root view (SectionView class) and paragraph (PageableParagraphView class)
//     *
//     * @author Stanislav Lapitsky
//     * @version 1.0
//     */
//    class PageableViewFactory implements ViewFactory {
//        /**
//         * Creates view for specified element.
//         * @param elem Element parent element
//         * @return View created view instance.
//         */
//        @Override
//        public View create(Element elem) {
//            String kind = elem.getName();
//            if (kind != null) {
//                switch (kind) {
//                    case AbstractDocument.ContentElementName:
//                        return new LabelView(elem);
//                    case AbstractDocument.ParagraphElementName:
//                        return new PageableParagraphView(elem);
//                    case AbstractDocument.SectionElementName:
//                        return new SectionView(elem, View.Y_AXIS);
//                    case StyleConstants.ComponentElementName:
//                        return new ComponentView(elem);
//                    case StyleConstants.IconElementName:
//                        return new IconView(elem);
//                }
//            }
//            // default to text display
//            return new LabelView(elem);
//        }
//
//    }
//
//    /**
//     * Root view which perform pagination and paints frame around pages.
//     *
//     * @author Stanislav Lapitsky
//     * @version 1.0
//     */
//    class SectionView extends BoxView {
//        int pageNumber = 0;
//
//        /**
//         * Creates view instace
//         * @param elem Element
//         * @param axis int
//         */
//        public SectionView(Element elem, int axis) {
//            super(elem, axis);
//        }
//
//        /**
//         * Gets amount of pages
//         * @return int
//         */
//        public int getPageCount() {
//            return pageNumber;
//        }
//
//        /**
//         * Perform layout on the box
//         *
//         * @param width the width (inside of the insets) >= 0
//         * @param height the height (inside of the insets) >= 0
//         */
//        @Override
//        protected void layout(int width, int height) {
//            width = pageWidth - 2 * DRAW_PAGE_INSET - pageMargins.left - pageMargins.right;
//            this.setInsets( (short) (DRAW_PAGE_INSET + pageMargins.top), (short) (DRAW_PAGE_INSET + pageMargins.left), (short) (DRAW_PAGE_INSET + pageMargins.bottom),
//                           (short) (DRAW_PAGE_INSET + pageMargins.right));
//            super.layout(width, height);
//        }
//
//        /**
//         * Determines the maximum span for this view along an
//         * axis.
//         *
//         * overriddedn
//         */
//        @Override
//        public float getMaximumSpan(int axis) {
//            return getPreferredSpan(axis);
//        }
//
//        /**
//         * Determines the minimum span for this view along an
//         * axis.
//         *
//         * overriddedn
//         */
//        @Override
//        public float getMinimumSpan(int axis) {
//            return getPreferredSpan(axis);
//        }
//
//        /**
//         * Determines the preferred span for this view along an
//         * axis.
//         * overriddedn
//         */
//        @Override
//        public float getPreferredSpan(int axis) {
//            float span = 0;
//            if (axis == View.X_AXIS) {
//                span = pageWidth;
//            }
//            else {
//                span = pageHeight * getPageCount();
//            }
//            return span;
//        }
//
//        /**
//         * Performs layout along Y_AXIS with shifts for pages.
//         *
//         * @param targetSpan int
//         * @param axis int
//         * @param offsets int[]
//         * @param spans int[]
//         */
//        @Override
//        protected void layoutMajorAxis(int targetSpan, int axis, int[] offsets, int[] spans) {
//            super.layoutMajorAxis(targetSpan, axis, offsets, spans);
//            int totalOffset = 0;
//            int n = offsets.length;
//            pageNumber = 0;
//            for (int i = 0; i < n; i++) {
//                offsets[i] = totalOffset;
//                View v = getView(i);
//                if (v instanceof MultiPageView) {
//                    ( (MultiPageView) v).setBreakSpan(0);
//                    ( (MultiPageView) v).setAdditionalSpace(0);
//                }
//
//                if ( (offsets[i] + spans[i]) > (pageNumber * pageHeight - DRAW_PAGE_INSET * 2 - pageMargins.top - pageMargins.bottom)) {
//                    if ( (v instanceof MultiPageView) && (v.getViewCount() > 1)) {
//                        MultiPageView multipageView = (MultiPageView) v;
//                        int space = offsets[i] - (pageNumber - 1) * pageHeight;
//                        int breakSpan = (pageNumber * pageHeight - DRAW_PAGE_INSET * 2 - pageMargins.top - pageMargins.bottom) - offsets[i];
//                        multipageView.setBreakSpan(breakSpan);
//                        multipageView.setPageOffset(space);
//                        multipageView.setStartPageNumber(pageNumber);
//                        multipageView.setEndPageNumber(pageNumber);
//                        int height = (int) getHeight();
//
//                        int width = ( (BoxView) v).getWidth();
//                        if (v instanceof PageableParagraphView) {
//                            PageableParagraphView parView = (PageableParagraphView) v;
//                            parView.layout(width, height);
//                        }
//
//                        pageNumber = multipageView.getEndPageNumber();
//                        spans[i] += multipageView.getAdditionalSpace();
//                    }
//                    else {
//                        offsets[i] = pageNumber * pageHeight;
//                        pageNumber++;
//                    }
//                }
//                totalOffset = (int) Math.min( (long) offsets[i] + (long) spans[i], Integer.MAX_VALUE);
//            }
//        }
//
//        /**
//         * Paints view content and page frames.
//         * @param g Graphics
//         * @param a Shape
//         */
//        @Override
//        public void paint(Graphics g, Shape a) {
//            Rectangle alloc = (a instanceof Rectangle) ? (Rectangle) a : a.getBounds();
//            Shape baseClip = g.getClip().getBounds();
//            int pageCount = getPageCount();
//            Rectangle page = new Rectangle();
//            page.x = alloc.x;
//            page.y = alloc.y;
//            page.height = pageHeight;
//            page.width = pageWidth;
//            String sC = Integer.toString(pageCount);
//            for (int i = 0; i < pageCount; i++) {
//                page.y = alloc.y + pageHeight * i;
//                paintPageFrame(g, page, (Rectangle) baseClip);
//                g.setColor(Color.blue);
//                String sN = Integer.toString(i + 1);
//                String pageStr = "Page: " + sN;
//                pageStr += " of " + sC;
//                g.drawString(pageStr,
//                             page.x + page.width - 100,
//                             page.y + page.height - 3);
//            }
//            super.paint(g, a);
//            g.setColor(Color.gray);
//            // Fills background of pages
//            int currentWidth = (int) alloc.getWidth();
//            int currentHeight = (int) alloc.getHeight();
//            int x = page.x + DRAW_PAGE_INSET;
//            int y = 0;
//            int w = 0;
//            int h = 0;
//            if (pageWidth < currentWidth) {
//                w = currentWidth;
//                h = currentHeight;
//                g.fillRect(page.x + page.width, alloc.y, w, h);
//            }
//            if (pageHeight * pageCount < currentHeight) {
//                w = currentWidth;
//                h = currentHeight;
//                g.fillRect(page.x, alloc.y + page.height * pageCount, w, h);
//            }
//        }
//
//        /**
//         * Paints frame for specified page
//         * @param g Graphics
//         * @param page Shape page rectangle
//         * @param container Rectangle
//         */
//        public void paintPageFrame(Graphics g, Shape page, Rectangle container) {
//            Rectangle alloc = (page instanceof Rectangle) ? (Rectangle) page : page.getBounds();
//            if (container.intersection(alloc).height <= 0)
//                return;
//            Color oldColor = g.getColor();
//
//            //borders
//            g.setColor(Color.gray);
//            g.fillRect(alloc.x, alloc.y, alloc.width, DRAW_PAGE_INSET);
//            g.fillRect(alloc.x, alloc.y, DRAW_PAGE_INSET, alloc.height);
//            g.fillRect(alloc.x, alloc.y + alloc.height - DRAW_PAGE_INSET, alloc.width, DRAW_PAGE_INSET);
//            g.fillRect(alloc.x + alloc.width - DRAW_PAGE_INSET, alloc.y, DRAW_PAGE_INSET, alloc.height);
//
//            //frame
//            g.setColor(Color.black);
//            g.drawLine(alloc.x + DRAW_PAGE_INSET, alloc.y + DRAW_PAGE_INSET, alloc.x + alloc.width - DRAW_PAGE_INSET, alloc.y + DRAW_PAGE_INSET);
//            g.drawLine(alloc.x + DRAW_PAGE_INSET, alloc.y + DRAW_PAGE_INSET, alloc.x + DRAW_PAGE_INSET, alloc.y + alloc.height - DRAW_PAGE_INSET);
//            g.drawLine(alloc.x + DRAW_PAGE_INSET, alloc.y + alloc.height - DRAW_PAGE_INSET, alloc.x + alloc.width - DRAW_PAGE_INSET, alloc.y + alloc.height - DRAW_PAGE_INSET);
//            g.drawLine(alloc.x + alloc.width - DRAW_PAGE_INSET, alloc.y + DRAW_PAGE_INSET, alloc.x + alloc.width - DRAW_PAGE_INSET, alloc.y + alloc.height - DRAW_PAGE_INSET);
//
//            //shadow
//            g.fillRect(alloc.x + alloc.width - DRAW_PAGE_INSET, alloc.y + DRAW_PAGE_INSET + 4, 4, alloc.height - 2 * DRAW_PAGE_INSET);
//            g.fillRect(alloc.x + DRAW_PAGE_INSET + 4, alloc.y + alloc.height - DRAW_PAGE_INSET, alloc.width - 2 * DRAW_PAGE_INSET, 4);
//
//            g.setColor(oldColor);
//        }
//
//    }
//
//    /**
//     * Represents multipage paragraph.
//     *
//     * @author Stanislav Lapitsky
//     * @version 1.0
//     */
//    class PageableParagraphView extends ParagraphView implements MultiPageView {
//        protected int additionalSpace = 0;
//        protected int breakSpan = 0;
//        protected int pageOffset = 0;
//        protected int startPageNumber = 0;
//        protected int endPageNumber = 0;
//
//        public PageableParagraphView(Element elem) {
//            super(elem);
//        }
//
//        @Override
//        public void layout(int width, int height) {
//            super.layout(width, height);
//        }
//
//        @Override
//        protected void layoutMajorAxis(int targetSpan, int axis, int[] offsets, int[] spans) {
//            super.layoutMajorAxis(targetSpan, axis, offsets, spans);
//            performMultiPageLayout(targetSpan, axis, offsets, spans);
//        }
//
//        /**
//         * Layout paragraph's content splitting between pages if needed.
//         * Calculates shifts and breaks for parent view (SectionView)
//         * @param targetSpan int
//         * @param axis int
//         * @param offsets int[]
//         * @param spans int[]
//         */
//        @Override
//        public void performMultiPageLayout(int targetSpan, int axis, int[] offsets, int[] spans) {
//            if (breakSpan == 0)
//                return;
//            int space = breakSpan;
//
//            additionalSpace = 0;
//            endPageNumber = startPageNumber;
//            int topInset = this.getTopInset();
//            int offs = 0;
//            for (int i = 0; i < offsets.length; i++) {
//                if (offs + spans[i] + topInset > space) {
//                    int newOffset = endPageNumber * pageHeight;
//                    int addSpace = newOffset - (startPageNumber - 1) * pageHeight - pageOffset - offs - topInset;
//                    additionalSpace += addSpace;
//                    offs += addSpace;
//                    for (int j = i; j < offsets.length; j++) {
//                        offsets[j] += addSpace;
//                    }
//                    endPageNumber++;
//                    space = (endPageNumber * pageHeight - 2 * DRAW_PAGE_INSET - pageMargins.top - pageMargins.bottom) - (startPageNumber - 1) * pageHeight - pageOffset;
//                }
//                offs += spans[i];
//            }
//        }
//
//        /**
//         * Gets view's start page number
//         * @return page number
//         */
//        @Override
//        public int getStartPageNumber() {
//            return startPageNumber;
//        }
//
//        /**
//         * Gets view's end page number
//         * @return page number
//         */
//        @Override
//        public int getEndPageNumber() {
//            return endPageNumber;
//        }
//
//        /**
//         * Gets view's extra space (space between pages)
//         * @return extra space
//         */
//        @Override
//        public int getAdditionalSpace() {
//            return additionalSpace;
//        }
//
//        /**
//         * Gets view's break span
//         * @return break span
//         */
//        @Override
//        public int getBreakSpan() {
//            return breakSpan;
//        }
//
//        /**
//         * Gets view's offsets on the page
//         * @return offset
//         */
//        @Override
//        public int getPageOffset() {
//            return pageOffset;
//        }
//
//        /**
//         * Sets view's start page number
//         *
//         * @param startPageNumber page number
//         */
//        @Override
//        public void setStartPageNumber(int startPageNumber) {
//            this.startPageNumber = startPageNumber;
//        }
//
//        /**
//         * Sets view's end page number
//         *
//         * @param endPageNumber page number
//         */
//        @Override
//        public void setEndPageNumber(int endPageNumber) {
//            this.endPageNumber = endPageNumber;
//        }
//
//        /**
//         * Sets extra space (space between pages)
//         *
//         * @param additionalSpace additional space
//         */
//        @Override
//        public void setAdditionalSpace(int additionalSpace) {
//            this.additionalSpace = additionalSpace;
//        }
//
//        /**
//         * Sets view's break span.
//         *
//         * @param breakSpan break span
//         */
//        @Override
//        public void setBreakSpan(int breakSpan) {
//            this.breakSpan = breakSpan;
//        }
//
//        /**
//         * Sets view's offset on the page
//         *
//         * @param pageOffset offset
//         */
//        @Override
//        public void setPageOffset(int pageOffset) {
//            this.pageOffset = pageOffset;
//        }
//    }
//    
//    public interface MultiPageView {
//
//    /**
//     * Perform pageable layout of componet's children views.
//     *
//     * @param targetSpan available view's span
//     * @param axis view axis (in most cases Y_AXIS.
//     * @param offsets children offsets
//     * @param spans children spans
//     */
//    public void performMultiPageLayout(int targetSpan, int axis, int[] offsets, int[] spans);
//
//    /**
//     * Gets view's start page number
//     * @return page number
//     */
//    public int getStartPageNumber();
//
//    /**
//     * Gets view's end page number
//     * @return page number
//     */
//    public int getEndPageNumber();
//
//    /**
//     * Gets view's extra space (space between pages)
//     * @return extra space
//     */
//    public int getAdditionalSpace();
//
//    /**
//     * Gets view's break span
//     * @return break span
//     */
//    public int getBreakSpan();
//
//    /**
//     * Gets view's offsets on the page
//     * @return offset
//     */
//    public int getPageOffset();
//
//    /**
//     * Sets view's start page number
//     *
//     * @param startPageNumber page number
//     */
//    public void setStartPageNumber(int startPageNumber);
//
//    /**
//     * Sets view's end page number
//     *
//     * @param endPageNumber page number
//     */
//    public void setEndPageNumber(int endPageNumber);
//
//    /**
//     * Sets extra space (space between pages)
//     *
//     * @param additionalSpace additional space
//     */
//    public void setAdditionalSpace(int additionalSpace);
//
//    /**
//     * Sets view's break span.
//     *
//     * @param breakSpan break span
//     */
//    public void setBreakSpan(int breakSpan);
//
//    /**
//     * Sets view's offset on the page
//     *
//     * @param pageOffset offset
//     */
//    public void setPageOffset(int pageOffset);
//    }
}


