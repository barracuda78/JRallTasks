package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.DateQuery;
import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

//Парсер логов (2)
//Реализуй интерфейс UserQuery у класса LogParser:
//2.1. Метод getAllUsers() должен возвращать всех пользователей.
//2.2. Метод getNumberOfUsers() должен возвращать количество уникальных пользователей.
//2.3. Метод getNumberOfUserEvents() должен возвращать количество уникальных событий от определенного пользователя.
//2.4. Метод getUsersForIP() должен возвращать пользователей с определенным IP.
//Несколько пользователей могут использовать один и тот же IP.
//2.5. Метод getLoggedUsers() должен возвращать пользователей, которые делали логин.
//2.6. Метод getDownloadedPluginUsers() должен возвращать пользователей, которые скачали плагин.
//2.7. Метод getWroteMessageUsers() должен возвращать пользователей, которые отправили сообщение.
//2.8. Метод getSolvedTaskUsers(Date after, Date before) должен возвращать пользователей, которые решали любую задачу.
//2.9. Метод getSolvedTaskUsers(Date after, Date before, int task) должен возвращать пользователей, которые решали задачу с номером task.
//2.10. Метод getDoneTaskUsers(Date after, Date before) должен возвращать пользователей, которые решили любую задачу.
//2.11. Метод getDoneTaskUsers(Date after, Date before, int task) должен возвращать пользователей, которые решили задачу с номером task.
//
//
//Требования:
//1. Класс LogParser должен поддерживать интерфейс UserQuery.
//2. Метод getAllUsers() должен возвращать множество содержащее всех пользователей.
//3. Метод getNumberOfUsers(Date, Date) должен возвращать количество уникальных пользователей за выбранный период.
//4. Метод getNumberOfUserEvents(String, Date, Date) должен возвращать количество уникальных событий от переданного пользователя за выбранный период.
//5. Метод getUsersForIP(String, Date, Date) должен возвращать множество содержащее пользователей, которые работали с переданного IP адреса за выбранный период.
//6. Метод getLoggedUsers(Date, Date) должен возвращать множество содержащее пользователей, которые были залогинены за выбранный период.
//7. Метод getDownloadedPluginUsers(Date, Date) должен возвращать множество содержащее пользователей, которые скачали плагин за выбранный период.
//8. Метод getWroteMessageUsers(Date, Date) должен возвращать множество содержащее пользователей, которые отправили сообщение за выбранный период.
//9. Метод getSolvedTaskUsers(Date, Date) должен возвращать множество содержащее пользователей, которые решали любую задачу за выбранный период.
//10. Метод getSolvedTaskUsers(Date, Date, int task) должен возвращать множество содержащее пользователей, которые решали задачу с номером task за выбранный период.
//11. Метод getDoneTaskUsers(Date, Date) должен возвращать множество содержащее пользователей, которые решили любую задачу за выбранный период.
//12. Метод getDoneTaskUsers(Date, Date, int task) должен возвращать множество содержащее пользователей, которые решили задачу с номером task за выбранный период.
public class LogParser implements IPQuery, UserQuery, DateQuery {
    private Path logDir;
    private List<String> logLines;
    private Set<String> result;
    private Set<Date> dates;

    public LogParser(Path logDir) {
        this.logDir = logDir;
        logLines = getLogs();
    }

    public List<String> getLogs() {
        List<String> logLines;
        try {
            logLines = Files.walk(this.logDir)
                    .filter(p -> p.getFileName().toString().endsWith(".log"))
                    .map(p -> {
                        try {
                            return Files.readAllLines(p);
                        } catch (IOException e) {
                            return Collections.<String>emptyList();
                        }
                    })
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logLines = new ArrayList<>();
        }
        return logLines;
    }

    private boolean isBetween(Date date, Date after, Date before) {
        return (after == null || date.after(after) || date.equals(after)) &&
                (before == null || date.before(before) || date.equals(before));
    }

    private boolean isLess(Date date, Date after, Date before) {
        return (date.before(before) || date.equals(before));
    }

    private boolean isMore(Date date, Date after, Date before) {
        return (date.after(after) || date.equals(after));
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        result = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            Date date = log.getDate();
            if (after == null && before!= null) {
                if (isLess(date, after, before)) {
                    result.add(log.getIp());
                }
            } else if (before == null && after != null) {
                if (isMore(date, after, before)) {
                    result.add(log.getIp());
                }
            } else if (isBetween(date, after, before)) {
                result.add(log.getIp());
            }
        }
        return result.size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        result = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            Date date = log.getDate();
            if (isBetween(date, after, before)) {
                result.add(log.getIp());
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        result = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            Date date = log.getDate();
            if (log.getUser().equals(user)) {
                if (isBetween(date, after, before)) {
                    result.add(log.getIp());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        result = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            Date date = log.getDate();
            if (log.getEvent().compareTo(event.toString()) == 0) {
                if (isBetween(date, after, before)) {
                    result.add(log.getIp());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        result = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            Date date = log.getDate();
            if (log.getStatus().compareTo(status.toString()) == 0) {
                if (isBetween(date, after, before)) {
                    result.add(log.getIp());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getAllUsers() {
        result = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            result.add(log.getUser());
        }
        return result;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        result = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            Date date = log.getDate();
            if (after == null && before!= null) {
                if (isLess(date, after, before)) {
                    result.add(log.getUser());
                }
            } else if (before == null && after != null) {
                if (isMore(date, after, before)) {
                    result.add(log.getUser());
                }
            } else if (isBetween(date, after, before)) {
                result.add(log.getUser());
            }
        }
        return result.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        result = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            if (log.getUser().equals(user)) {
                if (isBetween(log.getDate(), after, before)) {
                    result.add(log.getEvent());
                }
            }
        }
        return result.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        result = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            if (isBetween(log.getDate(), after, before)) {
                if (log.getIp().equals(ip)) {
                    result.add(log.getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        result = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            if (isBetween(log.getDate(), after, before)) {
                if (log.getEvent().compareTo(Event.LOGIN.toString()) == 0) {
                    result.add(log.getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        result = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            if (isBetween(log.getDate(), after, before)) {
                if (log.getEvent().compareTo(Event.DOWNLOAD_PLUGIN.toString()) == 0) {
                    result.add(log.getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        result = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            if (isBetween(log.getDate(), after, before)) {
                if (log.getEvent().compareTo(Event.WRITE_MESSAGE.toString()) == 0) {
                    result.add(log.getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        result = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            if (isBetween(log.getDate(), after, before)) {
                if (log.getEvent().compareTo(Event.SOLVE_TASK.toString()) == 0) {
                    result.add(log.getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        result = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            if (isBetween(log.getDate(), after, before)) {
                if (log.getEvent().compareTo(Event.SOLVE_TASK.toString()) == 0
                        && log.getTask().equals(task)) {
                    result.add(log.getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        result = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            if (isBetween(log.getDate(), after, before)) {
                if (log.getEvent().compareTo(Event.DONE_TASK.toString()) == 0) {
                    result.add(log.getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        result = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            if (isBetween(log.getDate(), after, before)) {
                if (log.getEvent().compareTo(Event.DONE_TASK.toString()) == 0
                        && log.getTask().equals(task)) {
                    result.add(log.getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        dates = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            if (isBetween(log.getDate(), after, before)) {
                if (log.getEvent().compareTo(event.toString()) == 0
                        && log.getUser().equals(user)) {
                    dates.add(log.getDate());
                }
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        dates = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            if (isBetween(log.getDate(), after, before)) {
                if (log.getStatus().compareTo(Status.FAILED.toString()) == 0) {
                    dates.add(log.getDate());
                }
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        dates = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            if (isBetween(log.getDate(), after, before)) {
                if (log.getStatus().compareTo(Status.ERROR.toString()) == 0) {
                    dates.add(log.getDate());
                }
            }
        }
        return dates;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        dates = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            if (isBetween(log.getDate(), after, before)) {
                if (log.getUser().equals(user) && (log.getEvent().compareTo(Event.LOGIN.toString()) == 0)) {
                    dates.add(log.getDate());
                }
            }
        }
        Date minDate = new Date();
        if (dates.size() == 0) {
            return null;
        } else {
            for (Date mDate: dates) {
                if (mDate.before(minDate)) {
                    minDate = mDate;
                }
            }
            return minDate;
        }
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        dates = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            if (isBetween(log.getDate(), after, before)) {
                if (log.getUser().equals(user) && (log.getEvent().compareTo(Event.SOLVE_TASK.toString()) == 0)) {
                    dates.add(log.getDate());
                }
            }
        }
        Date minDate = new Date();
        if (dates.size() == 0) {
            return null;
        } else {
            for (Date mDate: dates) {
                if (mDate.before(minDate)) {
                    minDate = mDate;
                }
            }
            return minDate;
        }
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        dates = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            if (isBetween(log.getDate(), after, before)) {
                if (log.getUser().equals(user) && (log.getEvent().compareTo(Event.DONE_TASK.toString()) == 0)) {
                    dates.add(log.getDate());
                }
            }
        }
        Date minDate = new Date();
        if (dates.size() == 0) {
            return null;
        } else {
            for (Date mDate: dates) {
                if (mDate.before(minDate)) {
                    minDate = mDate;
                }
            }
            return minDate;
        }
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        dates = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            if (isBetween(log.getDate(), after, before)) {
                if (log.getUser().equals(user) && (log.getEvent().compareTo(Event.WRITE_MESSAGE.toString()) == 0)) {
                    dates.add(log.getDate());
                }
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        dates = new HashSet<>();
        for(String str: logLines) {
            Log log = new Log(str);
            if (isBetween(log.getDate(), after, before)) {
                if (log.getUser().equals(user) && (log.getEvent().compareTo(Event.DOWNLOAD_PLUGIN.toString()) == 0)) {
                    dates.add(log.getDate());
                }
            }
        }
        return dates;
    }
}