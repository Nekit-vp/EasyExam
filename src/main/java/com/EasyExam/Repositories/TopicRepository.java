package com.EasyExam.Repositories;


import com.EasyExam.Entities.Topic;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;


import java.sql.Types;
import java.util.ArrayList;

@Repository
public class TopicRepository implements IRestRepository<Topic> {

    protected final JdbcOperations jdbcOperations;

    private static final String selectQuery = "SELECT \"id\", \"id_lesson\", \"number_task\", \"theory\", \"files\" " +
            "FROM \"topics\" " +
            "ORDER BY \"id\"";

    private static final String selectByIdQuery = "SELECT \"id\", \"id_lesson\", \"number_task\", \"theory\", \"files\" " +
            "FROM \"topics\" " +
            "WHERE \"id\" = ?";

    private static final String insertQuery = "INSERT INTO \"topics\"(\"id_lesson\", \"number_task\", \"theory\", \"files\") " +
            "VALUES (?, ?, ?, ?) " +
            "RETURNING \"id\", \"id_lesson\", \"number_task\", \"theory\", \"files\"";

    private static final String updateQuery = "UPDATE \"topics\" " +
            "SET \"id_lesson\" = ?, \"number_task\" = ?, \"theory\" = ?, \"files\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"id_lesson\", \"number_task\", \"theory\", \"files\"";

    private static final String deleteQuery = "DELETE FROM \"topics\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"id_lesson\", \"number_task\", \"theory\", \"files\"";

    public TopicRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Override
    public Topic[] select() {
        ArrayList<Topic> values = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (sqlRowSet.next()) {
            values.add(new Topic(
                    sqlRowSet.getInt(1),
                    sqlRowSet.getInt(2),
                    sqlRowSet.getInt(3),
                    sqlRowSet.getString(4),
                    sqlRowSet.getString(5)));
        }
        Topic[] result = new Topic[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Topic select(Integer id) {
        Object[] params = new Object[]{id};
        int[] types = new int[]{Types.INTEGER};
        SqlRowSet sqlrowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!sqlrowSet.next()) {
            return null;
        }
        return new Topic(
                sqlrowSet.getInt(1),
                sqlrowSet.getInt(2),
                sqlrowSet.getInt(3),
                sqlrowSet.getString(4),
                sqlrowSet.getString(5));
    }

    @Override
    public Topic insert(Topic entity) {
        Object[] params = new Object[]{entity.getId_lesson(), entity.getNumber_task(), entity.getTheory(), entity.getFiles()};
        int[] types = new int[]{Types.INTEGER, Types.INTEGER, Types.VARCHAR,Types.VARCHAR};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Topic(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getString(4),
                rowSet.getString(5));
    }

    @Override
    public Topic update(Integer id, Topic entity) {
        Object[] params = new Object[] {entity.getId_lesson(), entity.getNumber_task(), entity.getTheory(), entity.getFiles(), id };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.VARCHAR,Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Topic(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getString(4),
                rowSet.getString(5));
    }

    @Override
    public Topic delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Topic(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getString(4),
                rowSet.getString(5));
    }
}
