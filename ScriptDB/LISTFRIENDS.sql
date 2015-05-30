CREATE TABLE listfriends
(
  "ID" serial NOT NULL,
  "User" text,
  "Friend" text,
  CONSTRAINT listfriends_pkey PRIMARY KEY ("ID"),
    CONSTRAINT "lisfriends_User_fkey" FOREIGN KEY ("User")
      REFERENCES accounts ("UserName"),
      CONSTRAINT "lisfriends_Friend_fkey" FOREIGN KEY ("Friend")
      REFERENCES accounts ("UserName")

  
)