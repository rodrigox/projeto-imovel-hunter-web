package br.com.imovelhunterweb.util;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Email {

	private Properties propiedadesEmail;
	private Authenticator autenticacaoEmail;
	private Session sessaoEmail;
	
	private String nomeRemetente;
	
	private List<String> listaNome;
	private List<String> listaEmail;
	
	private String login = "imovelhunter";
	private String nossogmail = "imovelhunter@gmail.com";
	private String nossaSenha = "imovelhunter1234";

	public Email(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
		
		this.propiedadesEmail = new Properties();
		this.propiedadesEmail.setProperty("mail.transport.protocol", "smtp");
		this.propiedadesEmail.setProperty("mail.host","smtp.gmail.com"); 														
		this.propiedadesEmail.setProperty("mail.smtp.port", "587");
		this.propiedadesEmail.setProperty("mail.mime.charset", "ISO-8859-1");
		this.propiedadesEmail.setProperty("mail.smtp.auth", "true");
		this.propiedadesEmail.setProperty("mail.smtp.starttls.enable", "true");

		this.autenticacaoEmail = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Email.this.login,
						Email.this.nossaSenha);
			}
		};

		this.listaEmail = new ArrayList<String>();
		this.listaNome = new ArrayList<String>();
	}

	public void adicionarDestinatario(String nome, String email) {
		listaNome.add(nome);
		listaEmail.add(email);
	}

	/**
	 * Envia mensagem para a lista de destinatarios
	 * 
	 * @param assunto
	 *            assunto da mensagem
	 * @param mensagem
	 *            mensagem de corpo
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	public void enviar(String assunto, String mensagem) throws MessagingException, UnsupportedEncodingException {
		try {
			InternetAddress internetAddress;
			boolean first = true;
			
			this.sessaoEmail = Session.getDefaultInstance(this.propiedadesEmail, this.autenticacaoEmail);
			// session.setDebug(true);
			
			Message msg = new MimeMessage(this.sessaoEmail);			
			for (int i = 0 ; i < listaNome.size() ; i++) {
				internetAddress = new InternetAddress(this.listaEmail.get(i), this.listaNome.get(i));
				if (first) {
					// setamos o 1° destinatario
					msg.addRecipient(Message.RecipientType.TO, internetAddress);
					first = false;
				} else {
					msg.addRecipient(Message.RecipientType.CC, internetAddress);
				}
				
			}

			internetAddress = new InternetAddress(this.nossogmail, nomeRemetente);
			msg.setFrom(internetAddress);

			msg.setSubject(assunto);

			// Cria o objeto que recebe o texto do corpo do email
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setContent(mensagem, MailJava.TYPE_TEXT_PLAIN);

			// Monta a mensagem SMTP inserindo o conteudo, texto e anexos
			Multipart mps = new MimeMultipart();

			// adiciona o corpo texto da mensagem
			mps.addBodyPart(textPart);

			// adiciona a mensagem o conteúdo texto e anexo
			msg.setContent(mps);
			
			Transport transport = this.sessaoEmail.getTransport(); 
			transport.connect("smtp.gmail.com",this.nossogmail,this.nossaSenha);  			
//			System.out.println(transport.isConnected());
			
			Transport.send(msg);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new UnsupportedEncodingException();
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new MessagingException();
		}
	}
}