package br.com.imovelhunterweb.util;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class MailJava implements Serializable{

	private static final long serialVersionUID = -8946596996512256897L;
	
	//indica se o formato de texto será texto ou html
    public static final String TYPE_TEXT_PLAIN = "text/plain";
    public static final String TYPE_TEXT_HTML = "text/html";
    //indica qual será o servidor de email(gmail, hotmail...)        
    private String smtpHostMail;
    //indica a porta de acesso ao servidor
    private String smtpPortMail;
    //indica que a necessidade de autenticação no servidor(true ou false)
    private String smtpAuth;
    //indica ao servidor que ele está recebendo uma conexão segura
    private String smtpStarttls;
    //nome do remetente do email
    private String fromNameMail;
    //email do remetente
    private String userMail;
    //senha do email do remetente
    private String passMail;
    //assunto do email
    private String subjectMail;
    //corpo do email, onde estará o texto da mensagem
    private String bodyMail;
    //lista com email e nome dos destinatários
    private Map<String, String> toMailsUsers;
    //lista contendo os arquivos anexos
    private List<String> fileMails;
    //charset, no caso de html é necessário
    private String charsetMail;
    //tipo do formato da mensagem, texto ou html
    private String typeTextMail;
	/**
	 * 
	 */
	public MailJava() {
		super();
	}
	/**
	 * @param smtpHostMail
	 * @param smtpPortMail
	 * @param smtpAuth
	 * @param smtpStarttls
	 * @param fromNameMail
	 * @param userMail
	 * @param passMail
	 * @param subjectMail
	 * @param bodyMail
	 * @param toMailsUsers
	 * @param fileMails
	 * @param charsetMail
	 * @param typeTextMail
	 */
	public MailJava(String smtpHostMail, String smtpPortMail, String smtpAuth,
			String smtpStarttls, String fromNameMail, String userMail,
			String passMail, String subjectMail, String bodyMail,
			Map<String, String> toMailsUsers, List<String> fileMails,
			String charsetMail, String typeTextMail) {
		super();
		this.smtpHostMail = smtpHostMail;
		this.smtpPortMail = smtpPortMail;
		this.smtpAuth = smtpAuth;
		this.smtpStarttls = smtpStarttls;
		this.fromNameMail = fromNameMail;
		this.userMail = userMail;
		this.passMail = passMail;
		this.subjectMail = subjectMail;
		this.bodyMail = bodyMail;
		this.toMailsUsers = toMailsUsers;
		this.fileMails = fileMails;
		this.charsetMail = charsetMail;
		this.typeTextMail = typeTextMail;
	}
	/**
	 * @return the smtpHostMail
	 */
	public String getSmtpHostMail() {
		return smtpHostMail;
	}
	/**
	 * @param smtpHostMail the smtpHostMail to set
	 */
	public void setSmtpHostMail(String smtpHostMail) {
		this.smtpHostMail = smtpHostMail;
	}
	/**
	 * @return the smtpPortMail
	 */
	public String getSmtpPortMail() {
		return smtpPortMail;
	}
	/**
	 * @param smtpPortMail the smtpPortMail to set
	 */
	public void setSmtpPortMail(String smtpPortMail) {
		this.smtpPortMail = smtpPortMail;
	}
	/**
	 * @return the smtpAuth
	 */
	public String getSmtpAuth() {
		return smtpAuth;
	}
	/**
	 * @param smtpAuth the smtpAuth to set
	 */
	public void setSmtpAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
	}
	/**
	 * @return the smtpStarttls
	 */
	public String getSmtpStarttls() {
		return smtpStarttls;
	}
	/**
	 * @param smtpStarttls the smtpStarttls to set
	 */
	public void setSmtpStarttls(String smtpStarttls) {
		this.smtpStarttls = smtpStarttls;
	}
	/**
	 * @return the fromNameMail
	 */
	public String getFromNameMail() {
		return fromNameMail;
	}
	/**
	 * @param fromNameMail the fromNameMail to set
	 */
	public void setFromNameMail(String fromNameMail) {
		this.fromNameMail = fromNameMail;
	}
	/**
	 * @return the userMail
	 */
	public String getUserMail() {
		return userMail;
	}
	/**
	 * @param userMail the userMail to set
	 */
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	/**
	 * @return the passMail
	 */
	public String getPassMail() {
		return passMail;
	}
	/**
	 * @param passMail the passMail to set
	 */
	public void setPassMail(String passMail) {
		this.passMail = passMail;
	}
	/**
	 * @return the subjectMail
	 */
	public String getSubjectMail() {
		return subjectMail;
	}
	/**
	 * @param subjectMail the subjectMail to set
	 */
	public void setSubjectMail(String subjectMail) {
		this.subjectMail = subjectMail;
	}
	/**
	 * @return the bodyMail
	 */
	public String getBodyMail() {
		return bodyMail;
	}
	/**
	 * @param bodyMail the bodyMail to set
	 */
	public void setBodyMail(String bodyMail) {
		this.bodyMail = bodyMail;
	}
	/**
	 * @return the toMailsUsers
	 */
	public Map<String, String> getToMailsUsers() {
		return toMailsUsers;
	}
	/**
	 * @param toMailsUsers the toMailsUsers to set
	 */
	public void setToMailsUsers(Map<String, String> toMailsUsers) {
		this.toMailsUsers = toMailsUsers;
	}
	/**
	 * @return the fileMails
	 */
	public List<String> getFileMails() {
		return fileMails;
	}
	/**
	 * @param fileMails the fileMails to set
	 */
	public void setFileMails(List<String> fileMails) {
		this.fileMails = fileMails;
	}
	/**
	 * @return the charsetMail
	 */
	public String getCharsetMail() {
		return charsetMail;
	}
	/**
	 * @param charsetMail the charsetMail to set
	 */
	public void setCharsetMail(String charsetMail) {
		this.charsetMail = charsetMail;
	}
	/**
	 * @return the typeTextMail
	 */
	public String getTypeTextMail() {
		return typeTextMail;
	}
	/**
	 * @param typeTextMail the typeTextMail to set
	 */
	public void setTypeTextMail(String typeTextMail) {
		this.typeTextMail = typeTextMail;
	}
	/**
	 * @return the typeTextPlain
	 */
	public static String getTypeTextPlain() {
		return TYPE_TEXT_PLAIN;
	}
	/**
	 * @return the typeTextHtml
	 */
	public static String getTypeTextHtml() {
		return TYPE_TEXT_HTML;
	}
    
}
