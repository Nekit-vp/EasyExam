package com.EasyExam.Repositories;

import com.EasyExam.Entities.Lesson;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class LessonRepository implements IRestRepository<Lesson> {

    protected final JdbcOperations jdbcOperations;

    private static final String selectQuery = "SELECT \"id\", \"name\" " +
            "FROM \"lessons\" " +
            "ORDER BY \"id\"";

    private static final String selectByIdQuery = "SELECT \"id\", \"name\" " +
            "FROM \"lessons\" " +
            "WHERE \"id\" = ?";

    private static final String insertQuery = "INSERT INTO \"lessons\"(\"name\") " +
            "VALUES (?) " +
            "RETURNING \"id\", \"name\"";

    private static final String updateQuery = "UPDATE \"lessons\" " +
            "SET \"name\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\"";

    private static final String deleteQuery = "DELETE FROM \"lessons\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\"";

    public LessonRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Lesson[] select() {
        ArrayList<Lesson> values = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (sqlRowSet.next()) {
            values.add(new Lesson(
                    sqlRowSet.getInt(1),
                    sqlRowSet.getString(2)));
        }
        Lesson[] result = new Lesson[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Lesson select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Lesson(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Lesson insert(Lesson entity) {
        Object[] params = new Object[] { entity.getName() };
        int[] types = new int[] { Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Lesson(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Lesson update(Integer id, Lesson entity) {
        Object[] params = new Object[] { entity.getName(), id };
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Lesson(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Lesson delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Lesson(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }
}
