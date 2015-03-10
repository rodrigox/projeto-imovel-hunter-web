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
	private Authenticator autenticacaoEmail; // FIXME: ver quais atributos sao  nescessarios ficar aqui
	private Session sessaoEmail;
	
	private String nomeRemetente;
	
	private List<String> listaNome;
	private List<String> listaEmail;

	public Email(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
		
		this.propiedadesEmail = new Properties();
		this.propiedadesEmail.setProperty("mail.transport.protocol", "smtp");
		this.propiedadesEmail.setProperty("mail.host","smtp.superbutton.com.br"); // FIXME:
																			// configuracao,
																			// apenas
																			// o
																			// lado
//		this.propiedadesEmail.setProperty("mail.smtp.socketFactory.port","587");
//	    this.propiedadesEmail.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");																	// dierito"
		this.propiedadesEmail.setProperty("mail.smtp.port", "587");
		this.propiedadesEmail.setProperty("mail.mime.charset", "ISO-8859-1");
		this.propiedadesEmail.setProperty("mail.smtp.auth", "true");
		this.propiedadesEmail.setProperty("mail.smtp.starttls.enable", "true");

		this.autenticacaoEmail = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("automato",
						"bottomup14092011");
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

			internetAddress = new InternetAddress("automato@superbutton.com.br", nomeRemetente);
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
			transport.connect("smtp.superbutton.com.br","automato@superbutton.com.br","bottomup14092011");  			
			System.out.println(transport.isConnected());
			
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