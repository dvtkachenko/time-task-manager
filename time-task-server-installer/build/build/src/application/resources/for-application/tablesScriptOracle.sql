CREATE table "USERS" (
    "ID"              NUMBER NOT NULL,
    "LOGIN"           VARCHAR2(255) NOT NULL,
    "PASSWORD"        VARCHAR2(255) NOT NULL,
    "TASKLISTVERSION" NUMBER,
    constraint  "USERS_PK" primary key ("ID")
)       ;

CREATE table "TASKS" (
    "ID"                    NUMBER NOT NULL,
    "USERID"                NUMBER NOT NULL,
    "TASKNAME"              VARCHAR2(255) NOT NULL,
    "PARENTTASKID"          NUMBER,
    "CREATIONTIME"          VARCHAR2(255) NOT NULL,
    "FINISHTIME"            VARCHAR2(255),
    "SUGGESTEDTASKDURATION" VARCHAR2(255) NOT NULL,
    "ELAPSEDTASKDURATION"   VARCHAR2(255) NOT NULL,
    "FINISHED"              VARCHAR2(5) NOT NULL,
    "COMMENTS"   VARCHAR2(255),
    constraint  "TASKS_PK" primary key ("ID")
)         ;

--           
-- This is the data for the correct operation of the program
--

INSERT INTO users (id, login, password, tasklistversion) VALUES
(-1, 'zerouser', 'zerouser', '0');                      
    
INSERT INTO tasks (id, userid, taskname, parenttaskid, creationtime, finishtime, suggestedtaskduration, elapsedtaskduration, finished, comments) VALUES
(-1, -1, 'zero task', NULL, '0', '0', '0', '0', 'true', NULL);


CREATE sequence "USERS_SEQ"  ;
                         
alter table "USERS" add
constraint "USERS_UK1"
unique ("ID","LOGIN")   ;

CREATE trigger "BI_USERS"
  before insert on "USERS"
  for each row
begin
  if :NEW."ID" is null then
    select "USERS_SEQ".nextval into :NEW."ID" from dual;
  end if;
end;
/

CREATE sequence "TASKS_SEQ";

ALTER TABLE "TASKS" ADD CONSTRAINT "TASKS_FK"
FOREIGN KEY ("USERID")
REFERENCES "USERS" ("ID")
ON DELETE CASCADE;


ALTER TABLE "TASKS" ADD CONSTRAINT "TASKS_PARENT"
FOREIGN KEY ("PARENTTASKID")
REFERENCES "TASKS" ("ID")
ON DELETE CASCADE;

         
CREATE trigger "BI_TASKS"
  before insert on "TASKS"
  for each row
begin
  if :NEW."ID" is null then
    select "TASKS_SEQ".nextval into :NEW."ID" from dual;
  end if;
end;
/
