
package Olympics;

public class Medal
{
    //Attributes:
    public enum MedalType {BRONZE,SILVER,GOLD;}
    private String tournament; //באיזה טורניר זכה במדליה
    private Integer year; // באיזה שנה הוא זכה
    private MedalType type;
    //constructor:
    public Medal(String tournament, Integer year, MedalType type)
    {
        this.tournament = tournament;
        this.year = year;
        this.type = type;
    }
    //get:
    public String getTournament(){ return tournament; }
    public Integer getYear(){ return year;  }
    public MedalType getType(){ return type; }
    //set:
    public void setTournament(String tournament){ this.tournament = tournament;}
    public void setYear(Integer year){ this.year = year;}
    public void setType(MedalType type){ this.type = type;}
    //equal
    @Override
    public boolean equals(Object obj){

        if (this == obj)
            return true;

        if (obj == null || this.getClass() != obj.getClass())
            return false;

        Medal other = (Medal) obj;

        return tournament.equals(other.tournament) && year.equals(other.year) && type.equals(other.type);
    }
    //toString
    @Override
    public String toString() {
        return "Medal{" +
                "tournament='" + tournament + '\'' +
                ", year=" + year +
                ", type=" + type +
                '}';
    }
}

