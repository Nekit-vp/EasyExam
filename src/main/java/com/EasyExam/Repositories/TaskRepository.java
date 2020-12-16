package com.EasyExam.Repositories;

import com.EasyExam.Entities.Task;
import com.EasyExam.Entities.Topic;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class TaskRepository implements IRestRepository<Task> {

    protected final JdbcOperations jdbcOperations;

    public TaskRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String selectQuery = "SELECT \"id\", \"id_topic\", \"task_text\", \"answer\", \"complexity\" " +
            "FROM \"tasks\" " +
            "ORDER BY \"id\"";

    private static final String selectByIdQuery = "SELECT \"id\", \"id_topic\", \"task_text\", \"answer\", \"complexity\" " +
            "FROM \"tasks\" " +
            "WHERE \"id\" = ?";

    private static final String insertQuery = "INSERT INTO \"tasks\"(\"id_topic\", \"task_text\", \"answer\", \"complexity\") " +
            "VALUES (?, ?, ?, ?) " +
            "RETURNING \"id\", \"id_topic\", \"task_text\", \"answer\", \"complexity\"";

    private static final String updateQuery = "UPDATE \"tasks\" " +
            "SET \"id_topic\" = ?, \"task_text\" = ?, \"answer\" = ?, \"complexity\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"id_topic\", \"task_text\", \"answer\", \"complexity\"";

    private static final String deleteQuery = "DELETE FROM \"tasks\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"id_topic\", \"task_text\", \"answer\", \"complexity\"";


    @Override
    public Task[] select() {
        ArrayList<Task> values = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (sqlRowSet.next()) {
            values.add(new Task(
                    sqlRowSet.getInt(1),
                    sqlRowSet.getInt(2),
                    sqlRowSet.getString(3),
                    sqlRowSet.getString(4),
                    sqlRowSet.getInt(5)));
        }
        Task[] result = new Task[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Task select(Integer id) {
        Object[] params = new Object[]{id};
        int[] types = new int[]{Types.INTEGER};
        SqlRowSet sqlrowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!sqlrowSet.next()) {
            return null;
        }
        return new Task(
                sqlrowSet.getInt(1),
                sqlrowSet.getInt(2),
                sqlrowSet.getString(3),
                sqlrowSet.getString(4),
                sqlrowSet.getInt(5));
    }

    @Override
    public Task insert(Task entity) {
        Object[] params = new Object[]{entity.getId_topic(), entity.getTask_text(), entity.getAnswer(), entity.getComplexity()};
        int[] types = new int[]{Types.INTEGER, Types.VARCHAR, Types.VARCHAR,Types.INTEGER};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Task(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getInt(5));
    }

    @Override
    public Task update(Integer id, Task entity) {
        Object[] params = new Object[] {entity.getId_topic(), entity.getTask_text(), entity.getAnswer(), entity.getComplexity(), id };
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR,Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Task(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getInt(5));
    }

    @Override
    public Task delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Task(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getInt(5));
    }
}
