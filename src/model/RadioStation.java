package model;

import broadcast.Broadcasting;
import broadcast.broadcasters.Broadcaster;
import broadcast.broadcasters.WorkingBroadcaster;
import broadcast.sections.Advertising;
import broadcast.sections.Interview;
import broadcast.sections.Song;
import broadcast.sections.abstraction.BroadcastSection;
import data_base.DBHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class RadioStation {
    public static final int BROADCASTS_COUNT = 10;
    public static final int BROADCASTS_DURATION_LOWER_BOUND = 30;
    public static final int BROADCASTS_DURATION_UPPER_BOUND = 180;
    private static List<Broadcaster> broadcasters = new ArrayList<>();
    private static List<Song> songs = new ArrayList<>();
    private static List<Interview> interviews = new ArrayList<>();
    private static List<Advertising> advertisingList = new ArrayList<>();
    private static List<Broadcasting> broadcasts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        getDataFromDB();
        broadcasters.add(new Broadcaster("Ethan Cooper", "Experienced communicator with strong TV presentation skills"));//запрошений ведучий
        fillRandBroadcasts();
        while (true) {
            System.out.println("Список трансляцій:");
            for (int i = 0;i < broadcasts.size();i++) {
                System.out.print((i + 1) + ") ");
                broadcasts.get(i).shortInfo();
            }
            System.out.println("1 - обрати трансляцію\n2 - показати список ведучих\n" +
                    "3 - змінити трансляції\n4 - завершити роботу\n");
            int choose = scanner.nextInt();
            if(choose == 1){
                while (true) {
                    System.out.print("Оберіть номер трансляції або введіть символ щоб вийти -> ");
                    try {
                        choose = scanner.nextInt();
                        if (choose > 10)
                            choose = 10;
                        else if (choose < 1)
                            choose = 1;
                        choose--;
                        while (true){
                            System.out.println("1 - показати детальну інформацію\n2 - показати прибуток\n" +
                                    "3 - повернутись на попередній крок");
                            int choice = scanner.nextInt();
                            switch (choice) {
                                case 1 -> broadcasts.get(choose).showInfo();
                                case 2 -> broadcasts.get(choose).showIncome();
                            }
                            if (choice != 1 && choice != 2)
                                break;
                        }
                    }
                    catch (InputMismatchException exception){
                        scanner.next();
                        break;
                    }
                }
            }
            else if(choose == 2){
                for (Broadcaster broadcaster : broadcasters) {
                    broadcaster.showInfo();
                    System.out.println();
                }
            }
            else if(choose == 3)
                fillRandBroadcasts();
            else
                break;
        }
    }
    private static void fillRandBroadcasts(){
        broadcasts.clear();
        Random rand = new Random();
        for(int i = 0;i < BROADCASTS_COUNT;i++){
            broadcasts.add(new Broadcasting(rand.nextInt(BROADCASTS_DURATION_LOWER_BOUND, BROADCASTS_DURATION_UPPER_BOUND),
                    broadcasters.get(rand.nextInt(0, broadcasters.size())), randDate()));
            while (true){
                BroadcastSection section;
                int randSection = rand.nextInt(0, 11);//для того щоб була справедлива пропорція між
                if(randSection < 5)                                         //частинами трансляції(пісень має бути більше)
                    section = songs.get(rand.nextInt(0, songs.size()));
                else if(randSection == 10)
                    section = interviews.get(rand.nextInt(0, interviews.size()));
                else
                    section = advertisingList.get(rand.nextInt(0, advertisingList.size()));
                if(broadcasts.get(i).addSection(section).equals("Broadcasting duration exceeded")){
//якщо тривалість частин трансляції перевищує допустиму, то спроба доповнити цю трансляцію
// більш короткими частинами(тобто без інтерв'ю)
                    while (true){
                        randSection = rand.nextInt(0, 5);
                        if(randSection < 3)
                            section = songs.get(rand.nextInt(0, songs.size()));
                        else
                            section = advertisingList.get(rand.nextInt(0, advertisingList.size()));
                        if(broadcasts.get(i).addSection(section).equals("Broadcasting duration exceeded"))
                            break;
                    }
                    break;
                }
            }
        }
    }
    private static void getDataFromDB(){
        DBHandler db = new DBHandler();
        ResultSet resultSet = null;
        for(int i = 0; i < 4;i++){//для проходження по всім 4 спискам
            switch (i) {
                case 0 -> resultSet = db.getAllBroadcasters();//в залежності від ітерації
                case 1 -> resultSet = db.getAllSongs();//змінююється метод отримання даних
                case 2 -> resultSet = db.getAllInterviews();
                case 3 -> resultSet = db.getAllAdvertising();
            }
                try {
                    while (resultSet.next()){
                        switch (i) {
                            case 0 -> broadcasters.add(new WorkingBroadcaster(resultSet.getString(2),
                                    resultSet.getString(3), resultSet.getInt(4)));
                            case 1 -> songs.add(new Song(resultSet.getString(2),
                                    resultSet.getString(3), resultSet.getInt(4)));
                            case 2 -> interviews.add(new Interview(resultSet.getString(2),
                                    resultSet.getInt(3)));
                            case 3 -> advertisingList.add(new Advertising(resultSet.getString(2),
                                    resultSet.getInt(3)));
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
    private static LocalDate randDate(){//генерує випадкову дату минулих 7 днів
        Random rand = new Random();
        LocalDate now = LocalDate.now();
        return now.minusDays(rand.nextLong(0, 8));
    }
}
