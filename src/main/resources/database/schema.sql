create TABLE "lessons"
(    "id" SERIAL PRIMATY KEY,
    "name" TEXT DEFAULT '' NOT NULL);
create TABLE "topics"
(	"id" serial PRIMARY KEY,
	"id_lesson" INTEGER NOT NULL REFERENCES "lessons"("id"),
	"number_task" INTEGER NOT NULL UNIQUE,
	"theory" TEXT DEFAULT '' NOT NULL,
	"files" TEXT DEFAULT '' NOT NULL);
create TABLE "tasks"
(	"id" serial PRIMARY KEY,
	"id_topic" INTEGER NOT NULL REFERENCES "topics"("id"),
	"task_text" TEXT DEFAULT '' NOT NULL,
	"answer" TEXT DEFAULT '' NOT NULL,
	"complexity" INTEGER NOT NULL);
create TABLE "users"
(	"id" serial PRIMARY KEY,
	"login" TEXT NOT NULL UNIQUE,
	"password" TEXT NOT NULL,
	"role_user" BOOLEAN NOT NULL);
CREATE TABLE "solutions" (
	"id" serial PRIMARY KEY NOT NULL,
	"id_user" INTEGER NOT NULL REFERENCES "users"("id"),
	"id_task" INTEGER NOT NULL REFERENCES "tasks"("id"),
	"answer_user" TEXT NOT NULL,
	"mark" BOOLEAN NOT NULL);