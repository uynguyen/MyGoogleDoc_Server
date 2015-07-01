CREATE TABLE history
(
  "ID" serial NOT NULL,
  "User" text,
  "Content" text,
  "DateTime" date,
  "Action" text,
  "ChangePosition" text,
  CONSTRAINT history_pkey PRIMARY KEY ("ID"),
 CONSTRAINT "history_User_fkey" FOREIGN KEY ("User")
      REFERENCES accounts ("UserName")	
  
)