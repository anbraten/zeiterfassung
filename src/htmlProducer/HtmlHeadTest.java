package htmlProducer;

import sun.plugin.dom.html.HTMLElement;

import static htmlProducer.HtmlFactory.*;
import static org.junit.Assert.*;

public class HtmlHeadTest {



    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.Test
    public void getContentString() {

        HtmlTagElement th1 = TH.build().addText("Title 1");
        HtmlTagElement th2 = TH.build().addText("Title 2");

        HtmlTagElement tr = TR.build().addElement(th1, th2);

        String html = tr.getHTMLCode();
        assertNotNull(html.charAt(0) == '<');




        HtmlTagElement testHtml =
                HTML.build().addElement(
                        HEAD.build().addElement(TITLE.build().addText("Tolle Seite")),
                        BODY.build().addElement(
                                TABLE.build().addElement(
                                        TR.build().addElement(TH.build().addText("Title 1"), TH.build().addText("Title 2")),
                                        TR.build().addElement(TD.build().addText("Content 11"), TD.build().addText("Content 12")),
                                        TR.build().addElement(TD.build().addText("Content 21"), TD.build().addText("Content 22"))
                                )
                        )
                );

        html = testHtml.getHTMLCode();

        assertNotNull(html.charAt(0) == '<');
    }
}