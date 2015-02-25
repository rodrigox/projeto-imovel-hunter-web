package br.com.imovelhunterweb.util;

import java.sql.Timestamp; 
import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.Calendar; 
import java.util.Date; 

public class DataUtil {

	/**
	 * Cria uma data
	 * 
	 * @param dia
	 *            O dia
	 * @param mes
	 *            O mês
	 * @param ano
	 *            O ano
	 * @return Uma instância da classe Date
	 */
	public static Date criarData(int dia, int mes, int ano) {
		Calendar calendario;

		calendario = Calendar.getInstance();
		calendario.set(ano, mes - 1, dia, 0, 0, 0);
		return calendario.getTime();
	}

	/**
	 * Cria uma data
	 * 
	 * @param dia
	 *            O dia
	 * @param mes
	 *            O mês
	 * @param ano
	 *            O ano
	 * @param hora
	 *            A hora
	 * @param minuto
	 *            Os minutos
	 * @return Uma instância da classe Date
	 */
	public static Date criarData(int dia, int mes, int ano, int hora, int minuto) {
		Calendar calendario;

		calendario = Calendar.getInstance();
		calendario.set(ano, mes - 1, dia, hora, minuto, 0);
		return calendario.getTime();
	}

	/**
	 * Criar uma data
	 * 
	 * @param formatoData
	 *            yyyy-MM-dd
	 * @return
	 */
	public static Date criarData(String dataFormatada) {
		Date data = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			data = format.parse(dataFormatada);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * Cria uma data do tipo java.sql.Date
	 * 
	 * @param dataUtil
	 *            Um objeto java.util.Date
	 * @return Uma instância da classe Date
	 */
	public static java.sql.Date criarDataSQL(java.util.Date dataUtil) {
		java.sql.Date retorno;
		retorno = new java.sql.Date(dataUtil.getTime());
		return retorno;
	}

	public static Date criarDataUtil(java.sql.Date dataSQL) {
		java.util.Date retorno;
		retorno = new java.util.Date(dataSQL.getTime());
		return retorno;
	}

	/**
	 * Cria uma hora
	 * 
	 * @param hora
	 *            A hora
	 * @param minutos
	 *            Os minutos
	 * @param segundos
	 *            Os segundos
	 * @return Uma instância da classe Date
	 */
	public static Date criarHora(int hora, int minutos, int segundos) {
		Calendar calendario;

		calendario = Calendar.getInstance();
		calendario.set(Calendar.HOUR_OF_DAY, hora);
		calendario.set(Calendar.MINUTE, minutos);
		calendario.set(Calendar.SECOND, segundos);
		return calendario.getTime();
	}

	public static int obterDia(Date data) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(data);
		return calendario.get(Calendar.DATE);
	}

	public static int obterMes(Date data) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(data);
		return calendario.get(Calendar.MONTH) + 1;
	}

	public static int obterAno(Date data) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(data);
		return calendario.get(Calendar.YEAR);
	}

	/**
	 * Transforma um objeto Date para uma string no formato "dd/MM/aaaa"
	 * 
	 * @param data
	 *            O objeto Date a ser formatado.
	 * @return O texto contendo a data no formato especificado
	 * @exception AmbienteException
	 *                erro de ambiente (ex: arquivo não achado)
	 */
	public static String formatarData(Date data) {
		return formatarData(data, "dd/MM/yyyy");
	}

	public static String formatarDataCompleta(Date data) {
		return formatarData(data, "dd/MM/yyyy - HH:mm:ss");
	}
	
	public static String formatarDataCompleta2(Date data) {
		return formatarData(data, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * Transforma um objeto Date para uma string no formato especificado
	 * 
	 * @param data
	 *            O objeto Date a ser formatado.
	 * @param formato
	 *            O formato da data.
	 * @return O texto contendo a data no formato especificado
	 * @exception AmbienteException
	 *                erro de ambiente (ex: arquivo não achado)
	 */
	public static String formatarData(Date data, String formato) {
		SimpleDateFormat format;
		String dataFormatada;

		if (data != null) {
			format = new SimpleDateFormat(formato);
			dataFormatada = format.format(data);
		} else {
			dataFormatada = "";
		}
		return dataFormatada;
	}

	public static Timestamp obterTimestampAtual() {
		return new Timestamp(System.currentTimeMillis());
	}

//	public static void main(String[] args) {
//		Date data1 = DataUtil.criarData(01, 02, 2005);
//		System.out.println("Data1 formatada: " + DataUtil.formatarData(data1));
//
//		Date data2 = DataUtil.criarData("2005-09-29");
//		System.out.println("Data2 formatada: " + DataUtil.formatarData(data2));
//
//	}
}
