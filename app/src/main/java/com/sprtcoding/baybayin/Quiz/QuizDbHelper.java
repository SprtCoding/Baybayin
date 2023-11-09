package com.sprtcoding.baybayin.Quiz;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.sprtcoding.baybayin.Quiz.QuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        sqLiteDatabase.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Ang wika raw ay nagmula sa panggagaya ng mga sinaunang tao sa mga tunog na nilikha ng mga hayop","Dingdong","Sing-song","Bow-wow",2);
        addQuestion(q1);
        Question q2 = new Question("Batay sa teoryang ito, nagmula raw ang wika sa panggagaya ng mga sinaunang tao sa mga tunog ng kalikasan","Dingdong","Yo-he-ho","Biblikal",1);
        addQuestion(q2);
        Question q3 = new Question("Ayon sa teoryang ito may koneksiyon ang kumpas o galaw ng kamay ng tao sa paggalaw ng dila.","La-la","Ta-ta","Pooh-pooh",2);
        addQuestion(q3);
        Question q4 = new Question("Nagmula raw ang wika sa mga salitang namumutawi sa mga bibig ng sinaunang tao nang makaramdam sila ng masidhing damdamin tulad ng tuwa, galit, sarap, kalungkutan at pagkabigla","Yo-he-ho","Tarara-boom-de-ay","Pooh-pooh",3);
        addQuestion(q4);
        Question q5 = new Question("Nagmula raw ang wika sa pagsasama ng mga tao kapag nagtatrabaho o nagtitipon-tipon","Yo-he-ho","Sing-song","Bow-wow",1);
        addQuestion(q5);
        Question q6 = new Question("Ipinalagay sa teoryang ito na ang pagkakaiba-iba ng wika ay nagmula ng guluhin ng Dios ang wika ng tao dahil sa pagrerebelde at pagtatayo ng Tore ng Babel","Dingdong","Biblikal","Tarara-boom-de-ay",2);
        addQuestion(q6);
        Question q7 = new Question("Nakabatay raw ang unang wika sa melodiya at tonong pag-awit ng mga sinaunang tao, kagaya ng paghimig ng mga awit","Yum-yum","Tarara-boom-de-ay","Sing-song",3);
        addQuestion(q7);
        Question q8 = new Question("Sa teoryang ito tinutukoy ang mga pwersang may kinalaman sa romansa ang nagtulak sa mga tao na maghabi ng mga salita para sa mga tula at awit ng pag-ibig","La-la","Biblikal","Dingdong",1);
        addQuestion(q8);
        Question q9 = new Question("Tumutukoy sa pagmumula ng wika sa mga ritwal, pagdarasal, pagtatanim at iba pang gawain ng tao","Tarara-boom-de-ay","Yo-he-ho","Pooh-pooh",1);
        addQuestion(q9);
        Question q10 = new Question("Ito ay nakabatay sa Stimulus- Response Theory. Nakasaad dito ang pagtugon ng tao sa pamamagitan ng pagkumpas o pagpapakita ng aksyon upang ipahayag ang bagay na  nais sabihin","La-la","Yum-yum","Ta-ta",2);
        addQuestion(q10);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        this.db.insert(QuestionsTable.TABLE_NAME, null,cv);
    }

    @SuppressLint("Range")
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        this.db = getReadableDatabase();
        Cursor c = this.db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
