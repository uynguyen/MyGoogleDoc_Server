CREATE TABLE partnerdetails
(
  "ID" serial NOT NULL,
  "IDGroup" integer,
  "IDMember" integer,
  CONSTRAINT partnerdetails_pkey PRIMARY KEY ("ID"),
  CONSTRAINT "partnerdetails_ID_fkey" FOREIGN KEY ("IDMember")
      REFERENCES accounts ("ID")
)