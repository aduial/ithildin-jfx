/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aduial.ithildin.view;

import aduial.ithildin.entity.*;
import aduial.ithildin.repository.*;
import javafx.collections.ObservableList;
import javafx.scene.web.WebView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author luthien
 */
//@Component
public class LexiconPage{

    @Autowired
    private SimplexiconRepo simplexiconRepo;

    @Autowired
    private LexiconRepo lexiconRepo;
    private Lexicon     lexicon;

    @Autowired
    private RefRepo             refRepo;
    private ObservableList<Ref> refList;

    @Autowired
    private SpeechFormRepo speechformRepo;

    @Autowired
    private EntryNoteRepo entrynoteRepo;

    @Autowired
    private RefGlossRepo refGlossRepo;

    private List<Ref> refList;
    private Entry     parent;
    private WebView   wv1;
    private WebView   wv2;

    private boolean glsVisible = false;
    private boolean refVisible = false;
    private boolean drvVisible = false;
    private boolean iflVisible = false;
    private boolean elmVisible = false;
    private boolean cogVisible = false;


    private String table = "<table style='margin-top:3px; border: 1px solid black; border-collapse: collapse;'>";
    private String tr    = "<tr style='border: 1px solid black;'>";
    private String th    = "<th style='border: 1px solid black;'>";
    private String th10  = "<th style='border: 1px solid black; width: 10%;'>";
    private String th15  = "<th style='border: 1px solid black; width: 15%;'>";
    private String td    = "<td style='border: 1px solid black; padding: 2 4px;'>";


    public void setGlsVisible(boolean value) {
        glsVisible = value;
        writeContent();
    }

    public void setRefVisible(boolean value) {
        refVisible = value;
        writeContent();
    }

    public void setDrvVisible(boolean value) {
        drvVisible = value;
        writeContent();
    }

    public void setIflVisible(boolean value) {
        iflVisible = value;
        writeContent();
    }

    public void setElmVisible(boolean value) {
        elmVisible = value;
        writeContent();
    }

    public void setCogVisible(boolean value) {
        cogVisible = value;
        writeContent();
    }

    private String fontFamily(int family) {
        if (family < 1) {
            return "font-family: monaco, monospace;";
        } else if (family == 1) {
            return "font-family: 'Lucida Sans', 'Open Sans', sans-serif;";
        } else {
            return "font-family: Palatino, 'Lucida Bright', serif;";
        }
    }

    private String fontSize(int px) {
        return "font-size: " + px + "px;";
    }

    private String fontWeight(int weight) {
        return "font-weight: " + weight + "00;";
    }

    private String fontColour(String col) {
        return "color: #" + col + ";";
    }

    private String fontStyle(int style) {
        if (style < 1) {
            return "font-style: normal;";
        } else if (style == 1) {
            return "font-style: italic;";
        } else {
            return "font-style: oblique;";
        }
    }

    private String inline(int fam, int px, int wgh, int sty, String col) {
        return "style=\"" + fontFamily(fam) + fontSize(px) + fontWeight(wgh) + fontColour(col) + fontStyle(sty) + "\";";
    }

    public LexiconPage(Lexicon selectedLexicon, boolean refVisible, boolean glsVisible, boolean drvVisible,
                       boolean iflVisible, boolean elmVisible, boolean cogVisible) {
        this.lexicon    = selectedLexicon;
        this.refVisible = refVisible;
        this.glsVisible = glsVisible;
        this.drvVisible = drvVisible;
        this.iflVisible = iflVisible;
        this.elmVisible = elmVisible;
        this.cogVisible = cogVisible;
    }

    public void display(WebView wv1, WebView wv2) {
        this.wv1 = wv1;
        this.wv2 = wv2;
        writeContent();
    }

    private void writeContent() {
        String txt1 = writeHeadLine();
        String txt2 = writeWordNotes();
        refList = refRepo.findRefsByEntryId(lexicon.getId());
        if (refVisible) { txt1 += writeRefs(); }
        if (glsVisible) { txt1 += writeGlosses(); }
        if (drvVisible) { txt1 += writeDerivs(); }
        if (iflVisible) { txt1 += writeInflects(); }
        if (elmVisible) { txt1 += writeElements(); }
        if (cogVisible) { txt1 += writeCognates(); }
        wv1.getEngine().loadContent(txt1);
        wv2.getEngine().loadContent(txt2);
    }

    private String writeHeadLine() {
        String txt = "<p> " + spanTag(lexicon.getLangMnemonic() + ". ", 1, 14, 3, 0, "333");
        txt += spanTag(lexicon.getForm() + ", ", 1, 12, 7, 1, "336");
        txt += spanTag(speechformRepo.getOne(lexicon.getId()) + ". ", 1, 11, 5, 0, "000");
        txt += spanTag("\"" + lexicon.getGloss() + "\"", 2, 12, 4, 0, "555");
        txt += "</p>";
        return txt;
    }

    private String writeWordNotes() {
        String txt = "";
        for (EntryNoteView env : entrynoteRepo.findByEntryId(lexicon.getId())) {
            txt += optStylePtag(env.getTxt(), 1, 11, 4, 0, "222");
        }
        return txt;
    }

    private String writeRefs() {
        boolean rs      = false;
        String  sources = "";
        for (Ref ref : refList) {
            if (rs) {
                sources += "; ";
            }
            sources += ref.getSource() + " ";
            rs = true;
        }
        if (sources.length() == 0) {
            return "";
        } else {
            return "<p> " + spanTag("References: ", 1, 12, 7, 0, "228") + spanTag(sources, 1, 11, 4, 0, "222") + "</p>";
        }
    }

    private String writeGlosses() {
        boolean rg      = false;
        String  glosses = spanTag("Glosses: ", 1, 12, 7, 0, "228") + "<ul style=\"margin-top:3px;\">";
        for (RefGlossView refGlossView : refGlossRepo.findByEntryId(lexicon.getEntryId())) {
            glosses += "<li " + inline(1, 11, 4, 0, "222") + ">" + refGloss;
            rg = true;
        }
        if (rg) {
            return glosses + "</ul>";
        } else {
            return "";
        }
    }

    private String writeDerivs() {
        boolean dvs    = false;
        String  derivs = spanTag("Derivations: ", 1, 12, 7, 0, "228");
        derivs += table + "<tr>";
        derivs += th + spanTag("form ", 2, 11, 4, 2, "228") + "</th>";
        derivs += th15 + spanTag("gloss(es) ", 2, 11, 4, 2, "228") + "</th>";
        derivs += th + spanTag("sourc(es) ", 2, 11, 4, 2, "228") + "</th></tr>";

        for (RefderivView rdv : RefderivDAO.getRefderivList(lexicon.getId())) {
            derivs += "<tr>";
            derivs += td + spanTag(rdv.getForm(), 2, 12, 4, 1, "2B2") + "</td>" + td + spanTag(rdv.getGlosses(), 12, 11,
                                                                                               4, 0,
                                                                                               "B22") + "</td>" + td + spanTag(
                    rdv.getSources(), 1, 12, 4, 0, "555") + "</td>";
            derivs += "</tr>";
            dvs = true;
        }
        if (dvs) {
            return derivs + "</table></br>";
        } else {
            return "";
        }
    }

    private String writeInflects() {
        boolean ifl      = false;
        String  inflects = spanTag("Inflections: ", 1, 12, 7, 0, "228");
        inflects += table + "<tr>";
        inflects += th + spanTag("form(s) ", 2, 11, 4, 2, "228") + "</th>";
        inflects += th15 + spanTag("speech ", 2, 11, 4, 2, "228") + "</th>";
        inflects += th10 + spanTag("gloss ", 2, 11, 4, 2, "228") + "</th>";
        inflects += th + spanTag("sourc(es) ", 2, 11, 4, 2, "228") + "</th></tr>";

        for (RefinflectView refInflect : RefinflectDAO.getRefinflectList(lexicon.getId())) {
            String gloss = refInflect.getGloss();
            inflects += "<tr>";
            inflects += td + spanTag(refInflect.getForm(), 2, 12, 4, 1, "2B2") + "</td>" + td + spanTag(
                    refInflect.getGrammar(), 2, 12, 4, 0, "22B") + "</td>" + td + (
                                gloss != null ? spanTag(refInflect.getGloss(), 12, 11, 4, 0,
                                                        "B22") : "") + "</td>" + td + spanTag(refInflect.getSources(),
                                                                                              1, 12, 4, 0,
                                                                                              "555") + "</td>";
            inflects += "</tr>";
            ifl = true;
        }
        if (ifl) {
            return inflects + "</table><br>";
        } else {
            return "";
        }
    }

    private String writeElements() {
        boolean elm      = false;
        String  gloss, sources;
        String  elements = spanTag("Elements: ", 1, 12, 7, 0, "228");
        elements += "<ul style='margin-top:3px;'>";
        for (RefelementView refElement : RefelementDAO.getRefelementList(lexicon.getId())) {
            gloss   = null == refElement.getGloss() ? "" : refElement.getGloss();
            sources = null == refElement.getSources() ? "" : refElement.getSources();
            elements += "<li>";
            elements += spanTag(refElement.getLang() + ". ", 2, 11, 4, 0, "222");
            elements += spanTag(refElement.getForm() + " ", 2, 11, 4, 1, "228");
            if (! gloss.equalsIgnoreCase("")) { elements += spanTag(refElement.getGloss() + " ", 2, 11, 4, 0, "222"); }
            if (! sources.equalsIgnoreCase("")) {
                elements += spanTag(refElement.getSources() + " ", 1, 11, 4, 0, "222");
            }
            elements += "</li>";
            elm = true;
        }
        if (elm) {
            return elements + "</ul><br>";
        } else {
            return "";
        }
    }

    private String writeCognates() {
        boolean cog      = false;
        String  gloss, sources;
        String  cognates = spanTag("Cognates: ", 1, 12, 7, 0, "228");
        cognates += "<ul style='margin-top:3px;'>";
        for (RefcognateView refCognate : RefcognateDAO.getRefcognateList(lexicon.getId())) {
            gloss   = null == refCognate.getGloss() ? "" : refCognate.getGloss();
            sources = null == refCognate.getSources() ? "" : refCognate.getSources();
            cognates += "<li>";
            cognates += spanTag(refCognate.getLang() + ". ", 2, 11, 4, 0, "222");
            cognates += spanTag(refCognate.getForm() + " ", 2, 11, 4, 1, "228");
            if (! gloss.equalsIgnoreCase("")) { cognates += spanTag(refCognate.getGloss() + " ", 2, 11, 4, 0, "222"); }
            if (! sources.equalsIgnoreCase("")) {
                cognates += spanTag(refCognate.getSources() + " ", 1, 11, 4, 0, "222");
            }
            cognates += "</li>";
            cog = true;
        }
        if (cog) {
            return cognates + "</ul><br>";
        } else {
            return "";
        }
    }

    private String optStylePtag(String txt, int fam, int px, int wei, int sty, String col) {
        if (txt.toLowerCase().contains("<p>")) {
            return txt.replaceAll("<p>", "<p " + inline(fam, px, wei, sty, col) + ">");
        } else {
            return wholePtag(txt, fam, px, wei, sty, col);
        }
    }

    private String wholePtag(String txt, int fam, int px, int wei, int sty, String col) {
        return "<p " + inline(fam, px, wei, sty, col) + ">" + txt + "</p>";
    }

    private String spanTag(String txt, int fam, int px, int wei, int sty, String col) {
        return "<span " + inline(fam, px, wei, sty, col) + ">" + txt + "</span>";
    }

    private String startPtag(String txt, int fam, int px, int wei, int sty, String col) {
        return "<p " + inline(fam, px, wei, sty, col) + ">" + txt;
    }

}
