-- FORM definition

CREATE TABLE FORM (
    ID integer NOT NULL,
    TXT varchar(255) NOT NULL, NORMALTXT VARCHAR(255),
    PRIMARY KEY (ID)
);

CREATE INDEX idx_form_txt on form(txt);
CREATE INDEX FORM_NORMALTXT_idx on  FORM 
(
	NORMALTXT
);


-- GRAMMAR definition

CREATE TABLE GRAMMAR (
    ID integer NOT NULL,
    TXT varchar(100) NOT NULL,
    PRIMARY KEY (ID)
);


-- "user" definition

CREATE TABLE "user"
(
    id INTEGER PRIMARY KEY,
    firstname VARCHAR(60),
    lastname VARCHAR(60)
);


-- CAT definition

CREATE TABLE CAT (
    ID integer NOT NULL,
    LABEL varchar(255),
    PARENT_ID integer,
    PRIMARY KEY (ID),
    FOREIGN KEY (PARENT_ID) REFERENCES CAT (ID)
);


-- "LANGUAGE" definition

CREATE TABLE LANGUAGE (
    ID integer NOT NULL,
    NAME varchar(255) NOT NULL,
    MNEMONIC varchar(10),
    PARENT_ID integer,
    PRIMARY KEY (ID),
    FOREIGN KEY (PARENT_ID) REFERENCES LANGUAGE (ID)
);


-- "TYPE" definition

CREATE TABLE TYPE (
    ID integer NOT NULL,
    TXT varchar(50) NOT NULL,
    PARENT_ID integer,
    PRIMARY KEY (ID),
    FOREIGN KEY (PARENT_ID) REFERENCES TYPE (ID)
);


-- DOC definition

CREATE TABLE DOC (
    ID integer NOT NULL,
    TXT varchar(20000) NOT NULL,
    DOCTYPE_ID integer,
    PRIMARY KEY (ID),
    FOREIGN KEY (DOCTYPE_ID) REFERENCES TYPE (ID)
);


-- GLOSS definition

CREATE TABLE GLOSS (
    ID integer NOT NULL,
    LANGUAGE_ID integer NOT NULL,
    TXT varchar(255) NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (LANGUAGE_ID) REFERENCES LANGUAGE (ID)
);


-- LANGUAGE_DOC definition

CREATE TABLE LANGUAGE_DOC (
    LANGUAGE_ID integer NOT NULL,
    DOC_ID integer NOT NULL,
    ORDERING integer NOT NULL,
    PRIMARY KEY (LANGUAGE_ID,DOC_ID,ORDERING),
    FOREIGN KEY (LANGUAGE_ID) REFERENCES LANGUAGE (ID),
    FOREIGN KEY (DOC_ID) REFERENCES DOC (ID)
);


-- "SOURCE" definition

CREATE TABLE SOURCE (
    ID integer NOT NULL,
    NAME varchar(255),
    PREFIX varchar(255),
    SOURCETYPE_ID integer,
    PRIMARY KEY (ID),
    FOREIGN KEY (SOURCETYPE_ID) REFERENCES TYPE (ID)
);


-- SOURCE_DOC definition

CREATE TABLE SOURCE_DOC (
    SOURCE_ID integer NOT NULL,
    DOC_ID integer NOT NULL,
    ORDERING integer NOT NULL,
    PRIMARY KEY (SOURCE_ID,DOC_ID,ORDERING),
    FOREIGN KEY (DOC_ID) REFERENCES DOC (ID)
);


-- ENTRY definition

CREATE TABLE ENTRY (
    ID integer NOT NULL,
    FORM_ID integer NOT NULL,
    LANGUAGE_ID integer NOT NULL,
    GLOSS_ID integer,
    CAT_ID integer,
    RULE_FORM_ID integer,
    FROM_FORM_ID integer,
    STEM_FORM_ID integer,
    TENGWAR varchar(100),
    MARK varchar(10),
    ELDAMO_PAGEID varchar(50),
    ORDERFIELD varchar(50),
    ORTHO_FORM_ID integer,
    PARENT_ID integer,
    ORDERING integer,
    ENTRYTYPE_ID integer,
    PRIMARY KEY (ID),
    FOREIGN KEY (LANGUAGE_ID) REFERENCES LANGUAGE (ID),
    FOREIGN KEY (GLOSS_ID) REFERENCES GLOSS (ID),
    FOREIGN KEY (FROM_FORM_ID) REFERENCES FORM (ID),
    FOREIGN KEY (FORM_ID) REFERENCES FORM (ID),
    FOREIGN KEY (STEM_FORM_ID) REFERENCES FORM (ID),
    FOREIGN KEY (RULE_FORM_ID) REFERENCES FORM (ID),
    FOREIGN KEY (ENTRYTYPE_ID) REFERENCES TYPE (ID),
    FOREIGN KEY (CAT_ID) REFERENCES CAT (ID)
);

CREATE INDEX idx_entry_form_id on ENTRY(form_id);


-- ENTRY_DOC definition

CREATE TABLE ENTRY_DOC (
    ENTRY_ID integer NOT NULL,
    DOC_ID integer NOT NULL,
    ORDERING integer NOT NULL,
    FOREIGN KEY (DOC_ID) REFERENCES DOC (ID),
    FOREIGN KEY (ENTRY_ID) REFERENCES ENTRY (ID)
);


-- "REF" definition

CREATE TABLE REF (
    ID integer NOT NULL,
    ENTRY_ID integer,
    FORM_ID integer NOT NULL,
    GLOSS_ID integer,
    LANGUAGE_ID integer,
    SOURCE_ID integer NOT NULL,
    MARK varchar(10),
    RULE_FROMFORM_ID integer,
    RULE_RLFORM_ID integer,
    RULE_RULEFORM_ID integer,
    ORDERING integer,
    ENTRYTYPE_ID integer, SOURCE VARCHAR(255),
    PRIMARY KEY (ID),
    FOREIGN KEY (LANGUAGE_ID) REFERENCES LANGUAGE (ID),
    FOREIGN KEY (GLOSS_ID) REFERENCES GLOSS (ID),
    FOREIGN KEY (FORM_ID) REFERENCES FORM (ID),
    FOREIGN KEY (RULE_RLFORM_ID) REFERENCES FORM (ID),
    FOREIGN KEY (RULE_FROMFORM_ID) REFERENCES FORM (ID),
    FOREIGN KEY (RULE_RULEFORM_ID) REFERENCES FORM (ID),
    FOREIGN KEY (ENTRYTYPE_ID) REFERENCES TYPE (ID),
    FOREIGN KEY (ENTRY_ID) REFERENCES ENTRY (ID)
);

CREATE INDEX idx_ref_gloss_id on REF(gloss_id);
CREATE INDEX idx_ref_form_id on REF(form_id);


-- RULE definition

CREATE TABLE RULE (
    ID integer NOT NULL,
    ENTRY_ID integer NOT NULL,
    FROM_FORM_ID integer NOT NULL,
    RULE_FORM_ID integer NOT NULL,
    LANGUAGE_ID integer NOT NULL,
    ORDERING integer NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (RULE_FORM_ID) REFERENCES FORM (ID),
    FOREIGN KEY (FROM_FORM_ID) REFERENCES FORM (ID),
    FOREIGN KEY (LANGUAGE_ID) REFERENCES LANGUAGE (ID),
    FOREIGN KEY (ENTRY_ID) REFERENCES ENTRY (ID)
);


-- LINKED definition

CREATE TABLE LINKED (
    ID integer NOT NULL,
    LINKEDTYPE_ID integer NOT NULL,
    ENTRY_ID integer,
    REF_ID integer,
    TO_LANGUAGE_ID integer,
    TO_ENTRY_ID integer,
    ORDERING integer,
    SOURCE_ID integer,
    MARK varchar(10), SOURCE VARCHAR(255),
    PRIMARY KEY (ID),
    FOREIGN KEY (TO_LANGUAGE_ID) REFERENCES LANGUAGE (ID),
    FOREIGN KEY (TO_ENTRY_ID) REFERENCES ENTRY (ID),
    FOREIGN KEY (ENTRY_ID) REFERENCES ENTRY (ID),
    FOREIGN KEY (SOURCE_ID) REFERENCES SOURCE (ID),
    FOREIGN KEY (REF_ID) REFERENCES REF (ID),
    FOREIGN KEY (LINKEDTYPE_ID) REFERENCES TYPE (ID)
);

CREATE INDEX idx_linked_ref_id on LINKED(ref_id);
CREATE INDEX idx_linked_entry_id on LINKED(entry_id);


-- LINKED_FORM definition

CREATE TABLE LINKED_FORM (
    LINKED_ID integer NOT NULL,
    FORM_ID integer NOT NULL,
    ORDERING integer NOT NULL,
    FOREIGN KEY (FORM_ID) REFERENCES FORM (ID),
    FOREIGN KEY (LINKED_ID) REFERENCES LINKED (ID)
);

CREATE UNIQUE INDEX idx_linked_form_pk on linked_form(linked_id, form_id, ordering);


-- LINKED_GRAMMAR definition

CREATE TABLE LINKED_GRAMMAR (
    LINKED_ID integer,
    ENTRY_ID integer,
    GRAMMAR_ID integer NOT NULL,
    ORDERING integer NOT NULL, GRAMMARTYPE_ID INT,
    FOREIGN KEY (ENTRY_ID) REFERENCES ENTRY (ID),
    FOREIGN KEY (LINKED_ID) REFERENCES LINKED (ID),
    FOREIGN KEY (GRAMMAR_ID) REFERENCES GRAMMAR (ID),
    FOREIGN KEY (GRAMMARTYPE_ID) REFERENCES TYPE (ID)
);

CREATE UNIQUE INDEX idx_linked_grammar_pk on linked_grammar(linked_id, entry_id, grammar_id, ordering, grammartype_id);


-- RULESEQUENCE definition

CREATE TABLE RULESEQUENCE (
    DERIV_ID integer NOT NULL,
    FROM_FORM_ID integer,
    LANGUAGE_ID integer,
    RULE_FORM_ID integer,
    STAGE_FORM_ID integer,
    TO_ENTRY_ID integer,
    ORDERING integer,
    FOREIGN KEY (DERIV_ID) REFERENCES LINKED (ID),
    FOREIGN KEY (TO_ENTRY_ID) REFERENCES ENTRY (ID),
    FOREIGN KEY (STAGE_FORM_ID) REFERENCES FORM (ID),
    FOREIGN KEY (RULE_FORM_ID) REFERENCES FORM (ID),
    FOREIGN KEY (FROM_FORM_ID) REFERENCES FORM (ID),
    FOREIGN KEY (LANGUAGE_ID) REFERENCES LANGUAGE (ID)
);


-- EXAMPLE definition

CREATE TABLE EXAMPLE (
    LINKED_ID integer,
    REF_ID integer,
    SOURCE_ID integer NOT NULL,
    FORM_ID integer NOT NULL,
    ORDERING integer,
    EXAMPLETYPE_ID integer, SOURCE VARCHAR(255),
    FOREIGN KEY (FORM_ID) REFERENCES FORM (ID),
    FOREIGN KEY (EXAMPLETYPE_ID) REFERENCES TYPE (ID),
    FOREIGN KEY (SOURCE_ID) REFERENCES SOURCE (ID),
    FOREIGN KEY (REF_ID) REFERENCES REF (ID),
    FOREIGN KEY (LINKED_ID) REFERENCES LINKED (ID)
);

-- beforeview source

CREATE VIEW beforeview AS
SELECT 	l.id 			linked_id, 
		 	l.entry_id 	from_entry_id, 
		 	e2.id 		to_entry_id FROM linked l
JOIN type t ON l.linkedtype_id = t.id
JOIN linked_form lf ON l.id = lf.linked_id
JOIN entry e1 ON e1.id = l.entry_id
LEFT OUTER JOIN entry e2 ON e2.form_id = lf.form_id AND e2.language_id = l.to_language_id
WHERE t.txt = 'before'
ORDER BY from_entry_id, to_entry_id ASC;


-- changeview source

CREATE VIEW changeview AS 
SELECT  l.ref_id          refidfrom,
        lf.ordering       lgorder, 
        f.txt             formtxt,
        s.name            sourcename, 
        s.prefix          sourceprefix, 
        l.source          sourcestring,
        t2.txt            sourcetypetxt FROM linked l
JOIN type t ON l.linkedtype_id = t.id
JOIN linked_form lf ON l.id = lf.linked_id
JOIN form f ON f.id = lf.form_id
LEFT OUTER JOIN source s ON l.source_id = s.id
LEFT OUTER JOIN type t2 ON s.sourcetype_id = t2.id
WHERE t.txt = 'change'
ORDER BY l.ref_id, lf.ordering ASC;


-- classview source

CREATE VIEW classview AS
SELECT 	l.entry_id 		entryid, 
			l.mark 			mark,  
			lg.ordering 	lgorder, 
			g.txt 			grammartxt, 
			t2.txt 			grammartypetxt, 
			g.id 				grammarid FROM linked l
JOIN type t ON l.linkedtype_id = t.id
JOIN linked_grammar lg ON l.id = lg.linked_id
JOIN grammar g ON g.id = lg.grammar_id
JOIN type t2 ON lg.grammartype_id = t2.id
WHERE t.txt = 'class'
ORDER BY l.entry_id, lg.ordering, t2.id ASC;


-- cognateview source

CREATE VIEW cognateview AS
SELECT  l.entry_id        entryidfrom, 
        e2.id             entryidto, 
        l.ref_id          refidfrom, 
        r.id              refidto, 
        ln.name           languageto, 
        f.txt             formtxt, 
        s.name            sourcename, 
        s.prefix          sourceprefix, 
        l.source          sourcestring,
        t2.txt            sourcetypetxt FROM linked l
JOIN type t ON l.linkedtype_id = t.id
JOIN linked_form lf ON l.id = lf.linked_id
LEFT OUTER JOIN ref r ON lf.form_id = r.form_id AND l.source_id = r.source_id
LEFT OUTER JOIN entry e2 ON e2.form_id = lf.form_id AND e2.language_id = l.to_language_id
JOIN form f ON f.id = lf.form_id
LEFT OUTER JOIN source s ON l.source_id = s.id
LEFT OUTER JOIN type t2 ON s.sourcetype_id = t2.id
LEFT OUTER JOIN language ln ON l.to_language_id = ln.id
WHERE t.txt = 'cognate'
ORDER BY l.entry_id, l.ref_id, e2.id, r.id ASC;


-- coolderivview source

-- CREATE VIEW coolderivview AS
-- SELECT entryidfrom AS entry_id, form,
-- REPLACE(REPLACE(group_concat(DISTINCT gloss), ',', '; '),';  ', '; ' ) AS glosses,
-- REPLACE(group_concat(DISTINCT source), ',', '; ')  AS sources FROM refderivview
-- GROUP BY entry_id, form;


-- correctionview source

CREATE VIEW correctionview AS
SELECT  l.ref_id          refidfrom,
        r.id              refidto,
        f.txt             formtxt,
        s.name            sourcename,
        s.prefix          sourceprefix,
        l.source          sourcestring,
        t2.txt            sourcetypetxt FROM linked l
                                                 JOIN type t ON l.linkedtype_id = t.id
                                                 JOIN linked_form lf ON l.id = lf.linked_id
                                                 JOIN form f ON f.id = lf.form_id
                                                 JOIN ref r ON lf.form_id = r.form_id AND l.source_id = r.source_id
                                                 LEFT OUTER JOIN source s ON l.source_id = s.id
                                                 LEFT OUTER JOIN type t2 ON s.sourcetype_id = t2.id
WHERE t.txt = 'correction'
ORDER BY l.ref_id, f.id, r.id ASC;


-- derivview source

CREATE VIEW derivview AS
SELECT  l.entry_id        entryidfrom,
        e2.id             entryidto,
        l.ref_id          refidfrom,
        r.id              refidto,
        ln.name           languageto,
        f.txt             formtxt,
        s.name            sourcename,
        s.prefix          sourceprefix,
        l.source          sourcestring,
        t2.txt            sourcetypetxt,
        g1.TXT            entrygloss,
        g2.TXT            refgloss,
        lf.ordering       lforder FROM linked l
                                           JOIN type t ON l.linkedtype_id = t.id
                                           JOIN linked_form lf ON l.id = lf.linked_id
                                           LEFT OUTER JOIN ref r ON lf.form_id = r.form_id AND l.source_id = r.source_id
                                           LEFT OUTER JOIN entry e2 ON e2.form_id = lf.form_id AND e2.language_id = l.to_language_id
                                           JOIN form f ON f.id = lf.form_id
                                           LEFT OUTER JOIN source s ON l.source_id = s.id
                                           LEFT OUTER JOIN type t2 ON s.sourcetype_id = t2.id
                                           LEFT OUTER JOIN language ln ON l.to_language_id = ln.id
                                           LEFT OUTER JOIN GLOSS g1 ON e2.GLOSS_ID = g1.ID
                                           LEFT OUTER JOIN GLOSS g2 ON r.GLOSS_ID = g2.ID
WHERE t.txt = 'deriv'
GROUP BY formtxt
ORDER BY l.entry_id, l.ref_id, lf.ordering, e2.id, r.id ASC;


-- elementview source

CREATE VIEW elementview AS
SELECT  l.entry_id        entryidfrom,
        e2.id             entryidto,
        l.ref_id          refidfrom,
        r.id              refidto,
        ln.name           languageto,
        f.txt             formtxt,
        g.txt             grammartxt,
        s.name            sourcename,
        s.prefix          sourceprefix,
        l.source          sourcestring,
        t2.txt            sourcetypetxt FROM linked l
                                                 JOIN type t ON l.linkedtype_id = t.id
                                                 JOIN linked_form lf ON l.id = lf.linked_id
                                                 LEFT OUTER JOIN ref r ON lf.form_id = r.form_id AND l.source_id = r.source_id
                                                 LEFT OUTER JOIN entry e2 ON e2.form_id = lf.form_id AND e2.language_id = l.to_language_id
                                                 JOIN form f ON f.id = lf.form_id
                                                 LEFT OUTER JOIN source s ON l.source_id = s.id
                                                 LEFT OUTER JOIN type t2 ON s.sourcetype_id = t2.id
                                                 LEFT OUTER JOIN language ln ON l.to_language_id = ln.id
                                                 LEFT OUTER JOIN linked_grammar lg ON lg.linked_id = l.id
                                                 LEFT OUTER JOIN grammar g ON g.id = lg.grammar_id
WHERE t.txt = 'element'
ORDER BY l.entry_id, l.ref_id, e2.id, r.id ASC;


-- entrynoteview source

CREATE VIEW entrynoteview as
select e.id entry_id, ed.ORDERING ordering, d.TXT txt from entry e
                                                               join entry_doc ed on e.ID = ed.ENTRY_ID
                                                               join doc d on ed.DOC_ID = d.ID
                                                               join type t on d.DOCTYPE_ID = t.ID
where t.TXT = 'notes'
order by ed.ORDERING;


-- inflectview source

CREATE VIEW inflectview AS
SELECT  l.entry_id        entryidfrom,
        l.ref_id          refidfrom,
        f.txt             formtxt,
        lg.ordering       lgorder,
        g.txt             inflection,
        tgram.txt         inflecttypetxt FROM linked l
                                                  JOIN type t ON l.linkedtype_id = t.id
                                                  LEFT OUTER JOIN linked_form lf ON lf.linked_id = l.id
                                                  LEFT OUTER JOIN form f ON f.id = lf.form_id
                                                  LEFT OUTER JOIN linked_grammar lg ON lg.linked_id = l.id
                                                  LEFT OUTER JOIN grammar g ON g.id = lg.grammar_id
                                                  LEFT OUTER JOIN type tgram ON lg.grammartype_id = tgram.id
WHERE t.txt = 'inflect'
ORDER BY l.entry_id, l.ref_id, tgram.id, lg.ordering ASC;


-- lexicon source

CREATE VIEW lexicon AS SELECT  e.id entry_id,
                               f.txt form,
                               l.mnemonic lang_mnemonic,
                               l.name lang_name,
                               g.txt gloss,
                               c.label cat,
                               e.tengwar tengwar,
                               e.mark mark,
                               e.eldamo_pageid eldamo_pageid,
                               e.orderfield orderfield,
                               e.parent_id parent_id,
                               e.ordering ordering,
                               e.entrytype_id entrytype_id,
                               t.txt entry_type
                       FROM ENTRY e
                                LEFT OUTER JOIN FORM f
                                                ON e.form_id = f.id
                                LEFT OUTER JOIN LANGUAGE l
                                                ON e.language_id = l.id
                                LEFT OUTER JOIN GLOSS g
                                                ON e.gloss_id = g.id
                                LEFT OUTER JOIN CAT c
                                                ON e.cat_id = c.id
                                LEFT OUTER JOIN TYPE t
                                                ON e.entrytype_id = t.id;


-- refcognateview source

CREATE VIEW refcognateview AS SELECT DISTINCT l.entry_id 		        entry_id,
                                              Upper(lang.mnemonic)
                                                  || '.'                                           lang,
                                              r1.mark
                                                  || f.txt                                         form,
                                              COALESCE(g1.txt, g2.txt)                         gloss,
                                              Group_concat(DISTINCT COALESCE (r1.source
                                                                                  || ' ('
                                                                                  || f11.txt
                                                                                  || ')', r2.source
                                                                                  || ' ('
                                                                                  || f22.txt
                                                                                  || ')')) sources
                              FROM   linked l
                                         LEFT OUTER JOIN linked_form lf
                                                         ON lf.linked_id = l.id
                                         JOIN form f
                                              ON f.id = lf.form_id
                                         JOIN ref r1
                                              ON r1.source = l.source
                                         JOIN entry e
                                              ON e.id = r1.entry_id
                                         LEFT OUTER JOIN gloss g1
                                                         ON e.gloss_id = g1.id
                                         JOIN language lang
                                              ON lang.id = e.language_id
                                         JOIN type t
                                              ON l.linkedtype_id = t.id
                                         JOIN ref r2
                                              ON r2.entry_id = e.id
                                         LEFT OUTER JOIN gloss g2
                                                         ON r2.gloss_id = g2.id
                                         JOIN form f11
                                              ON r1.form_id = f11.id
                                         JOIN form f22
                                              ON r2.form_id = f22.id
                              WHERE  t.txt = 'cognate'
                                AND r2.gloss_id IS NOT NULL
                                AND NOT EXISTS (SELECT *
                                                FROM   linked l2
                                                WHERE  l2.ref_id = r2.id)
                                AND r2.id != r1.id
                              GROUP  BY l.entry_id,
                                        form;


-- refderivview source

CREATE VIEW refderivview AS SELECT l.entry_id AS entry_id,
                                   f.txt      AS form,
                                   Replace(Replace(Group_concat(DISTINCT g.txt), ',', '; '), ';  ', '; ') AS glosses,
                                   Replace(Group_concat(DISTINCT l.source), ',', '; ')                    AS sources
                            FROM   linked l
                                       JOIN   linked_form lf
                                              ON     lf.linked_id = l.id
                                       JOIN   form f
                                              ON     f.id = lf.form_id
                                       LEFT JOIN ref r
                                                 ON     r.source = l.source AND r.form_id = f.id
                                       JOIN gloss g
                                            ON     r.gloss_id = g.id
                                       JOIN type t
                                            ON     l.linkedtype_id = t.id
                            WHERE  t.txt = 'deriv'
                              AND    r.entry_id != l.entry_id
                            GROUP  BY l.entry_id, form;


-- refelementview source

CREATE VIEW refelementview AS
SELECT e1.id entry_id
     , upper(lg.mnemonic) lang
     , e2f.TXT form
     , group_concat(DISTINCT COALESCE(e2g.txt, rg.txt)) gloss
     , ' ✧ ' || REPLACE(group_concat(DISTINCT r.source || CASE WHEN LOWER(rf.txt) = LOWER(e2f.TXT)
                                                                   THEN '' ELSE ' (' || rf.txt || ')' END),',', '; ') sources
FROM entry e1
         JOIN form f1 on e1.form_id = f1.id
         JOIN form f2 on f2.normaltxt = f1.normaltxt
         JOIN linked_form lf ON f2.id = lf.FORM_ID
         JOIN linked l ON l.id = lf.LINKED_ID
         LEFT OUTER JOIN ref r ON l.REF_ID = r.ID
         LEFT OUTER JOIN form rf ON rf.ID = r.FORM_ID
         LEFT OUTER JOIN gloss rg ON rg.ID = r.GLOSS_ID
         JOIN entry e2 ON l.ENTRY_ID = e2.ID
         LEFT OUTER JOIN form e2f ON e2f.ID = e2.FORM_ID
         LEFT OUTER JOIN gloss e2g ON e2g.ID = e2.GLOSS_ID
         JOIN language lg ON lg.ID = e2.LANGUAGE_ID
         JOIN type t ON l.LINKEDTYPE_ID = t.ID
WHERE t.txt = 'element'
GROUP BY e1.id, form;


-- refglossview source

CREATE VIEW refglossview AS
SELECT r.entry_id entry_id,
       '"' || g.txt || '" ✧ ' || GROUP_CONCAT(DISTINCT r.source SEPARATOR '; ') refgloss
FROM ref r
         JOIN gloss g ON g.id = r.gloss_id
WHERE NOT EXISTS (select * FROM linked l WHERE l.ref_id = r.id)
GROUP BY LOWER(g.txt);




-- refinflectview source

CREATE VIEW refinflectview AS
SELECT r.entry_id entry_id,
       f.txt form,
       gr.txt grammar,
       gl.txt gloss,
       ' ✧ ' || GROUP_CONCAT(r.source SEPARATOR '; ') sources
FROM ref r
         LEFT OUTER JOIN gloss gl ON gl.id = r.gloss_id
         JOIN form f ON f.id = r.form_id
         JOIN linked l ON l.REF_ID = r.ID
         LEFT OUTER JOIN linked_grammar lg ON lg.LINKED_ID = l.ID
         JOIN grammar gr ON gr.ID = lg.GRAMMAR_ID
         JOIN type t2 ON lg.GRAMMARTYPE_ID = t2.id
         JOIN type t ON l.LINKEDTYPE_ID = t.ID
WHERE t.txt = 'inflect'
GROUP BY LOWER(f.txt);


-- relatedview source

CREATE VIEW relatedview AS
SELECT  l.entry_id        entryidfrom,
        e2.id             entryidto,
        l.ref_id          refidfrom,
        r.id              refidto,
        ln.name           languageto,
        l.ordering        lorder,
        f.txt             formtxt,
        s.name            sourcename,
        s.prefix          sourceprefix,
        l.source          sourcestring,
        t2.txt            sourcetypetxt FROM linked l
                                                 JOIN type t ON l.linkedtype_id = t.id
                                                 JOIN linked_form lf ON l.id = lf.linked_id
                                                 LEFT OUTER JOIN ref r ON lf.form_id = r.form_id AND l.source_id = r.source_id
                                                 LEFT OUTER JOIN entry e2 ON e2.form_id = lf.form_id AND e2.language_id = l.to_language_id
                                                 JOIN form f ON f.id = lf.form_id
                                                 LEFT OUTER JOIN source s ON l.source_id = s.id
                                                 LEFT OUTER JOIN type t2 ON s.sourcetype_id = t2.id
                                                 LEFT OUTER JOIN language ln ON l.to_language_id = ln.id
WHERE t.txt = 'related'
ORDER BY l.entry_id, l.ref_id, l.ordering, e2.id, r.id ASC;


-- simplexicon source
CREATE VIEW simplexicon AS SELECT e.id           entry_id,
                                  e.mark         mark,
                                  f.txt          form,
                                  e.language_id  language_id,
                                  l.NAME         languagename,
                                  g.txt          gloss,
                                  c.label        cat,
                                  sf.txt         stem,
                                  e.entrytype_id entrytype_id
                           FROM   entry e
                                      JOIN form f
                                           ON e.form_id = f.id
                                      JOIN language l
                                           ON e.language_id = l.id
                                      JOIN gloss g
                                           ON e.gloss_id = g.id
                                      LEFT OUTER JOIN cat c
                                                      ON e.cat_id = c.id
                                      LEFT OUTER JOIN form sf
                                                      ON e.stem_form_id = sf.id
                           WHERE  e.entrytype_id IN ( 1028, 1033, 1034 );


-- speechformview source

CREATE VIEW speechformview AS SELECT DISTINCT lg.entry_id, g.txt FROM
    GRAMMAR g
        JOIN LINKED_GRAMMAR lg
             ON g.ID = lg.GRAMMAR_ID
        JOIN TYPE t
             ON lg.GRAMMARTYPE_ID = t.ID
                              WHERE t.TXT = 'speechform';