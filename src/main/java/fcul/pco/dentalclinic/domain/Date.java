package fcul.pco.dentalclinic.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* 
* @author Alexandre Nascimento nº50002 e Rodrigo Alcarva nº50011
*
*/

public class Date {
	private int dia;
	private int mes;
	private int ano;
	private int hora;
	private int minuto;
	private static final Date STARTDATE = new Date(1, 1, 2000, 0, 0);
	private static final int StartDateInt = STARTDATE.intValue();

	private static final Date[] Holydays = {
			new Date(1, 1),
			new Date(4, 25),
			new Date(5, 1),
			new Date(6, 10),
			new Date(6, 15),
			new Date(8, 15),
			new Date(10, 5),
			new Date(11, 1),
			new Date(12, 1),
			new Date(12, 8),
			new Date(12, 25)
	};
	
	/**
	 * Creates a date instance.
	 * @param d. The day
	 * @param m. The month
	 * @param y. The year
	 * @param h. The hour
	 * @param min. The minutes
	 */
	public Date(int d, int m, int y, int h, int min) {
		this.dia = d;
		this.mes = m;
		this.ano = y;
		this.hora= h;
		this.minuto = min;
	}

	private Date(int month, int day) {
		this.dia = day;
		this.mes = month;
	}

	/**
	 * 
	 * @return int that represents the day
	 */
	public int getDia() {
		return dia;
	}

	/**
	 * 
	 * @return int that represents the month
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * 
	 * @return int that represents the year
	 */
	public int getAno() {
		return ano;
	}

	/**
	 * 
	 * @return int that represents the hour
	 */
	public int getHora() {
		return hora;
	}

	/**
	 * 
	 * @return int that represents the minutes
	 */
	public int getMinuto() {
		return minuto;
	}

	/**
	 * @return string the represents the date
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(dia);
		sb.append("!");
		sb.append(mes);
		sb.append("!");
		sb.append(ano);
		sb.append("!");
		sb.append(hora);
		sb.append("!");
		sb.append(minuto);
		String s = sb.toString();
		return s;
	}

	/**
	 * 
	 * @param s. The string of a date
	 * @return a instance of date using the string
	 */
	public static Date fromString(String s) {
		String[] st = s.split("!");
		int dia = Integer.parseInt(st[0]);
		int mes = Integer.parseInt(st[1]);
		int ano = Integer.parseInt(st[2]);
		int hora = Integer.parseInt(st[3]);
		int minuto = Integer.parseInt(st[4]);

		Date d = new Date(dia, mes, ano, hora, minuto);

		return d;
	}	

	private boolean sameDay(Date other) {
		if (dia == other.dia && mes == other.mes) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return true if date is a holyday
	 */
	public boolean isHolyday() {
		for (int i = 0; i < Holydays.length; i++) {
			if (dia == Holydays[i].dia && mes == Holydays[i].mes) {
				return true;
			}
		}
		return false;
	}

	private static boolean isLeapYear(int year) {
		if((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))){
			return true;
		}
		return false;
	}


	private static int daysInMonth(int month, int year) {
		if (month == 2 && isLeapYear(year)) {
			return 29;
		}
		int []m = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		return m[month-1];
	}


	private static int daysInYear(int year) {
		if (isLeapYear(year)) {
			return 366;
		}
		return 365;
	}


	private int intValue() {
		int value = minuto;
		value += hora * 60;
		value += (dia - 1) * 1440;
		for (int m = 1; m < mes; m++) {
			value += daysInMonth(m, ano) * 1440;
		}
		for (int y = STARTDATE.ano; y < ano; y++) {
			value += daysInYear(y) * 1440;
		}
		return value - StartDateInt;
	}


	private int daysSinceStartDate()  {
		return this.intValue() / 1440;
	}


	/**
	 * Discovers week day of given date
	 * @return int representing day of week 
	 */
	public int dayOfWeek() {
		int n = this.daysSinceStartDate();
		n = n % 7;
		int[] dw = {5, 6, 0, 1, 2, 3, 4};
		return dw[n];
	}

	/**
	 * Compares two dates and discovers the earliest
	 * @param other. Date compared to
	 * @return true if date is before other
	 */
	public boolean isBefore (Date other) {
		if (this.intValue() < other.intValue()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets current date
	 * @return date of today
	 */
	public static Date getCurrentDate() {
		LocalDateTime now = LocalDateTime.now();
		return new Date(now.getDayOfMonth(), now.getMonthValue(), now.getYear(), now.getHour(), now.getMinute());
	}

	/**
	 * Gets tomorrow date
	 * @return date of tomorrow
	 */
	public static Date getTomorrowMorning() {
		Date now = getCurrentDate();
		int tomorrow = now.dia + 1;
		int mes = now.mes;
		int ano = now.ano;

		if (tomorrow > daysInMonth(now.mes, now.ano)) {
			mes += 1;
			tomorrow = 1;
			if (mes > 12) {
				ano += 1;
				mes = 1;
			}
		}
		
		Date d = new Date(tomorrow, mes, ano, 9 , 0);
		return d;
	}

	/**
	 * 
	 * @param every.  Duration of appointment
	 * @param exclude. List of dates of already assigned appointments
	 * @return
	 */
	public List<Date> makeSmartDateList(int every, List<Date> exclude) {
		List<Date> ls =  new ArrayList<Date>();
		int cmi = minuto-every;
		int ch = hora;
		int ca = ano;
		int cm = mes;
		int cd = dia;
		
		int num = 0;
		
		while (num < 10) {
			cmi += every;
			if (cmi >= 60) {
				ch ++;
				cmi -= 60;
			}
			
			if (ch >= 24) {
				cd ++;
				ch = 0;
			}
			
			if (cd > daysInMonth(cm, ca)) {
				cm += 1;
				cd = 1;
				if (cm > 12) {
					ca += 1;
					cm = 1;
				}
			}
				
			Date data = new Date(cd, cm, ca, ch, cmi);
			
			if (data.dayOfWeek() != 5 && data.dayOfWeek() != 6) {
				boolean notExclude = true;
				
				for (int i = 0; i < exclude.size(); i++) {
					if (data.toString().equals(exclude.get(i).toString())) {
						notExclude = false;
					}
				}
				
				boolean notHolyday = true;
				
				for (int j = 0; j < Holydays.length; j++) {
					if (Holydays[j] == data) {
						notHolyday = false;
					}
				}
				if (notExclude && notHolyday && (((ch >= 9) && (ch <12)) || ((ch >= 14) && (ch <18)))) {
					num ++;
					ls.add(data);
				}
			}
		}
		return ls;
	}
	
	/**
	 * 
	 * @param segunda. true if date is a monday
	 * @return date
	 */
	public Date seeWeek(boolean segunda) {
		int todayWeek = this.dayOfWeek();
		int day = this.getDia();
		int month = this.getMes();
		int year = this.getAno();
		
		if (segunda) {
			day = day - todayWeek;
		}
		
		else {
			day ++;
		}
		
		if (day < 0) {
			month --;
			day = daysInMonth(month, year);
		}
		
		if (day > daysInMonth(month, year)) {
			month ++;
			day = 1;
			if (month > 12) {
				year += 1;
				month = 1;
			}
		}
		
		Date d = new Date(day, month, year, 9, 0);
		return d;
	}
	
	/**
	 * 
	 * @param DateList. List of dates
	 * @return print of a table with all the appointments
	 */
	public static String dateListToString(List<Date> DateList) {
		List <List<String>> table = new ArrayList<List<String>>();
		for (int i = 0; i < DateList.size(); i++) {
			String[] date = DateList.get(i).toString().split("!");
			StringBuilder sb = new StringBuilder();
			sb.append(date[2]);
			sb.append("/");
			sb.append(date[1]);
			sb.append("/");
			sb.append(date[0]);
			sb.append("@");
			sb.append(date[3]);
			sb.append(":");
			sb.append(date[4]);
			
			String s = sb.toString();
			
			ArrayList<String> row = new ArrayList<String>();
			row.add(Integer.toString(i + 1));
			row.add(s);
			table.add(row);
		}
		return Utils.tableToString(table);
	}
		
}
