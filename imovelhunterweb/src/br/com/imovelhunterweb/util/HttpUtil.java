package br.com.imovelhunterweb.util;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class HttpUtil {

    private String url;
    private Map<String,String> parametros;

    public HttpUtil(String url){
        this.url = url;
        this.parametros = new HashMap<String, String>();
    }


    //#################
    //####publics#####
    //#################

    public void put(String chave,String valor){
        this.parametros.put(chave,valor);
    }



    public void clear(){
        this.parametros.clear();
    }


    public void setUrl(String url){
        this.url = url;
    }



    public String enviarRequest() throws IOException {

        URL obj = new URL(this.url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();


        con.setRequestMethod("POST");

        con.setDoInput(true);
        con.setDoOutput(true);

        OutputStream out =  con.getOutputStream();
        DataOutputStream wr = new DataOutputStream(out);
        wr.writeBytes(this.montarParametros());
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        con.disconnect();

        return response.toString();
    }
    
    public String enviarRequestGet() throws IOException{
    	byte ptext[] = this.url.getBytes(Charset.forName("ISO-8859-1")); 
    	String value = new String(ptext,Charset.forName("UTF-8")); 
    	
    	
    	URL obj = new URL(value);
    	
    	

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        
        con.setRequestProperty("User-Agent","Mozilla/5.0");
        
        int responseCode = con.getResponseCode();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        con.disconnect();

        return response.toString();
    }


    //##################
    //####ferramentas###
    //##################


    public List<?> jsonArrayToList(String jsonArray,Class<?> classe) throws JSONException, IllegalAccessException, InstantiationException {
        List<Object> lista = new ArrayList<Object>();

        JSONArray array = new JSONArray(jsonArray);

        int tam = array.length();

        for(int i = 0 ; i < tam ; i++){

            Object objeto = classe.newInstance();

            ((ObjetoJSON)objeto).parse(array.getJSONObject(i).toString());

            lista.add(objeto);

        }

        return lista;
    }

    public String listaParaJson(List<? extends ObjetoJSON<?>> lista) {
        StringBuffer stb = new StringBuffer();

        stb.append("[");

        int tam = 0;
        if(lista != null && (tam = lista.size()) > 0){

            int cont = 0;
            Boolean fim = false;

            for(Object o : lista){

                if(cont + 1 == tam){
                    fim = true;
                }


                if(fim){

                    stb.append(o.toString());

                }else{

                    stb.append(o.toString());
                    stb.append(",");

                }

                cont++;
            }


        }

        stb.append("]");


        return stb.toString();
    }





    //#################
    //####privates#####
    //#################

    private String montarParametros(){
        Iterator<String> chaves = this.parametros.keySet().iterator();
        StringBuffer buffer = new StringBuffer();

        Boolean removerUltimo = false;
        while(chaves.hasNext()){
            removerUltimo = true;
            String chave = chaves.next();
            String valor = this.parametros.get(chave);

            buffer.append(chave);
            buffer.append("=");
            buffer.append(valor);
            buffer.append("&");
        }

        if(removerUltimo){
            buffer.deleteCharAt(buffer.length());
        }


        return buffer.toString();
    }

}
