CREATE TABLE documents
(
  "ID" serial NOT NULL,
  "Name" text,
  "Path" text,
  "DateCreate" date,
  "IDOwner" integer,
  "IDPartners" integer,
  CONSTRAINT documents_pkey PRIMARY KEY ("ID"),
        CONSTRAINT "documents_IDOwner_fkey" FOREIGN KEY ("IDOwner")
      REFERENCES accounts ("ID")
)