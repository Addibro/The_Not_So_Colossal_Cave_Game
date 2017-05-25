package se.itu.game.cave.init;

import java.sql.*;

/**
 * A helper class for e.g. the initialization of a game from the
 * database.
 */
class DbUtil {

  private static final String DB_CLASS = "org.sqlite.JDBC";
  private static final String DB_URL   = "jdbc:sqlite:cavedatabas.db";


  // can be anywhere in the class
  // this static block only runs once
  // this runs first
  static {
    try {
      Class.forName(DB_CLASS);
    } catch (ClassNotFoundException cnfe) {
      System.err.println("Couldn't load class: " + cnfe);
    }
  }

  private static Connection con;
  private static DbUtil instance;
  
  private DbUtil() {
    try {
      con = DriverManager.getConnection(DB_URL);
    } catch (SQLException sqle) {
      System.err.println("Couldn't get connection to " + DB_URL + ": " + sqle.getMessage());
    }
  }

  /**
   * Returns the instance of this singleton class.
   * @return The reference to the only DBUtil instance
   */
  static DbUtil getInstance() {
    if (instance == null) {
      instance = new DbUtil();
    }
    return instance;
  }

  ResultSet query(String sql) throws SQLException {
    Statement st = con.createStatement();
    return st.executeQuery(sql);
  }

  /*
  public static void addHighScore(long score, String person) {
    String sqlQuery = "INSERT INTO HighScore (Score, Person) VALUES (?, ?)";

    try (PreparedStatement preparedStatement = con.prepareStatement(sqlQuery)){
      preparedStatement.setLong(1, score);
      preparedStatement.setString(2, person);
      preparedStatement.execute();
//      con.commit();
    } catch (SQLException ex) {
      System.err.println(ex.getClass().getSimpleName() + ": " + ex.getMessage());
    }
  }

  public static String getHighScore() {
    String sqlQuery =
            "SELECT Person FROM HighScore ORDER BY Score LIMIT 10";
    StringBuilder highScore = new StringBuilder();

    try {
      Statement statement = con.createStatement();
      ResultSet rs = statement.executeQuery(sqlQuery);
      int count = 1;
      while (rs.next()) {
        highScore.append(count)
                .append(". ")
                .append(rs.getString("Person"))
                .append("\n");
        count++;
      }
      System.out.println(highScore);
    } catch (SQLException ex) {
      System.err.println(ex.getClass().getSimpleName() + ": " + ex.getMessage());
    }
    return highScore.toString();
  }
  */
}
