package ru.yusdm.javacore.lesson22database.lesson;

/**
 * Created by Admin on 4/10/2019.
 */
public class A_SqlExample {
    /**
     *
     *
     *
     *    SELECT
     md.*
     FROM
     MARK mk, MODEL md
     WHERE
     (mk.NAME = 'Ford') AND (md.MARK_ID = mk.ID);


     SELECT
     md.*
     FROM
     MARK mk INNER JOIN  MODEL md
     ON (md.MARK_ID = mk.ID)
     WHERE
     (mk.NAME = 'Ford');

     */
}
