package com.example.appgestiondesconges;

public class Session {
    static String id;
    public Session(String x){
        id= x;
    }

    public String getId() {
        return id;
    }
}
/*Date sd = Date.valueOf(sdb.getValue());
            Date ed = Date.valueOf(edb.getValue());
            Date diff = new Date(sd.getTime() - ed.getTime());
            Calendar c = Calendar.getInstance();
            c.setTime(diff);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);*/
/*SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    java.util.Date dsd;
    java.util.Date ded;
    {
        try {
            dsd = formatter.parse(String.valueOf(sd));
            ded = formatter.parse(String.valueOf(ed));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }*/

//private final ObservableList<Employee> dataList = FXCollections.observableArrayList();

     /*public void load_data(){
        try {
            DBUtils con=new DBUtils();
            Connection condb=con.getConnection();
        var.clear();
        query="SELECT * FROM `leave` WHERE idemp='\" +String.valueOf(idemp1) + \"'";
        ps=condb.prepareStatement(query);
        rs=ps.executeQuery();
        while (rs.next()){
            var.add(new Leave1(
                    rs.getInt("idLeave"),
                    rs.getString("type"),
                    rs.getString("idemp"),
                    rs.getString("state"),
                    rs.getString("date_debut"),
                    rs.getString("date_fin"),
                    rs.getInt("dure")));
            //  LeaveTable.setItems(var);
        }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
     }*/
