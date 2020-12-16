package com.EasyExam.Repositories;

import com.EasyExam.Entities.Topic;
import com.EasyExam.Entities.User;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;


@Repository
public class UserRepository implements IRestRepository<User> {

    protected final JdbcOperations jdbcOperations;

    public UserRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String selectQuery = "SELECT \"id\", \"login\", \"password\" " +
            "FROM \"users\" " +
            "ORDER BY \"id\"";

    private static final String selectByIdQuery = "SELECT \"id\", \"login\", \"password\" " +
            "FROM \"users\" " +
            "WHERE \"id\" = ?";

    private static final String insertQuery = "INSERT INTO \"users\"(\"login\", \"password\") " +
            "VALUES (?, ?) " +
            "RETURNING \"id\", \"login\", \"password\"";

    private static final String updateQuery = "UPDATE \"users\" " +
            "SET \"login\" = ?, \"password\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"login\", \"password\"";

    private static final String deleteQuery = "DELETE FROM \"users\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"login\", \"password\"";

    @Override
    public User[] select() {
        ArrayList<User> values = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (sqlRowSet.next()) {
            values.add(new User(
                    sqlRowSet.getInt(1),
                    sqlRowSet.getString(2),
                    sqlRowSet.getString(3)));
        }
        User[] result = new User[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public User select(Integer id) {
        Object[] params = new Object[]{id};
        int[] types = new int[]{Types.INTEGER};
        SqlRowSet sqlrowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!sqlrowSet.next()) {
            return null;
        }
        return new User(
                sqlrowSet.getInt(1),
                sqlrowSet.getString(2),
                sqlrowSet.getString(3));
    }

    @Override
    public User insert(User entity) {
        Object[] params = new Object[]{entity.getLogin(), entity.getPassword()};
        int[] types = new int[]{Types.VARCHAR,Types.VARCHAR};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new User(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3));
    }

    @Override
    public User update(Integer id, User entity) {
        Object[] params = new Object[] {entity.getLogin(), entity.getPassword(), id };
        int[] types = new int[] {Types.VARCHAR,Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new User(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3));
    }

    @Override
    public User delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new User(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3));
    }
}
