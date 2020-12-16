package com.EasyExam.Repositories;

import com.EasyExam.Entities.Solution;
import com.EasyExam.Entities.Task;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class SolutionRepository implements IRestRepository<Solution>{

    protected final JdbcOperations jdbcOperations;

    public SolutionRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String selectQuery = "SELECT \"id\", \"id_user\", \"id_task\", \"answer_user\", \"mark\" " +
            "FROM \"solutions\" " +
            "ORDER BY \"id\"";

    private static final String selectByIdQuery = "SELECT \"id\", \"id_user\", \"id_task\", \"answer_user\", \"mark\" " +
            "FROM \"solutions\" " +
            "WHERE \"id\" = ?";

    private static final String insertQuery = "INSERT INTO \"solutions\"(\"id_user\", \"id_task\", \"answer_user\", \"mark\") " +
            "VALUES (?, ?, ?, ?) " +
            "RETURNING \"id\", \"id_user\", \"id_task\", \"answer_user\", \"mark\"";

    private static final String updateQuery = "UPDATE \"solutions\" " +
            "SET \"id_user\" = ?, \"id_task\" = ?, \"answer_user\" = ?, \"mark\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"id_user\", \"id_task\", \"answer_user\", \"mark\"";

    private static final String deleteQuery = "DELETE FROM \"solutions\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"id_user\", \"id_task\", \"answer_user\", \"mark\"";


    @Override
    public Solution[] select() {
        ArrayList<Solution> values = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (sqlRowSet.next()) {
            values.add(new Solution(
                    sqlRowSet.getInt(1),
                    sqlRowSet.getInt(2),
                    sqlRowSet.getInt(3),
                    sqlRowSet.getString(4),
                    sqlRowSet.getBoolean(5)));
        }
        Solution[] result = new Solution[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Solution select(Integer id) {
        Object[] params = new Object[]{id};
        int[] types = new int[]{Types.INTEGER};
        SqlRowSet sqlrowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!sqlrowSet.next()) {
            return null;
        }
        return new Solution(
                sqlrowSet.getInt(1),
                sqlrowSet.getInt(2),
                sqlrowSet.getInt(3),
                sqlrowSet.getString(4),
                sqlrowSet.getBoolean(5));
    }

    @Override
    public Solution insert(Solution entity) {
        Object[] params = new Object[]{entity.getId_user(), entity.getId_task(), entity.getAnswer_user(), entity.getMark()};
        int[] types = new int[]{Types.INTEGER, Types.INTEGER, Types.VARCHAR,Types.BOOLEAN};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);

        if (!rowSet.next()) {
            return null;
        }
        return new Solution(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getString(4),
                rowSet.getBoolean(5));
    }

    @Override
    public Solution update(Integer id, Solution entity) {
        Object[] params = new Object[] {entity.getId_user(), entity.getId_task(), entity.getAnswer_user(), entity.getMark(), id };
        int[] types = new int[] {Types.INTEGER, Types.INTEGER, Types.VARCHAR,Types.BOOLEAN, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Solution(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getString(4),
                rowSet.getBoolean(5));
    }

    @Override
    public Solution delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Solution(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getString(4),
                rowSet.getBoolean(5));
    }
}
