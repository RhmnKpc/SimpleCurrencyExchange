package com.rkpc.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rkpc.model.CurrencyObject;
import com.rkpc.model.DovizComCurrencyObject;
import com.rkpc.model.Rates;

public class CurrencyService {

    private String dovizUrl = "https://www.doviz.com/api/v1/currencies/";

    public CurrencyService() {

    }

    /**
     * Exchange işleminin yapıldığı metot
     *
     * @param val1
     * @param base
     * @return
     */
    public CurrencyObject exchangeCurrency(double val1, String base) {
        try {
            //Parametre olarak gönderilen kurun değerini alıyoruz döviz.com üzerinden

            DovizComCurrencyObject baseDoviz = getDovizComCurrency(base);
            //Eğer geriye null dönerse TL olarak kabul ediyorum çünkü deneme yaptığım kurlar içerisinde tl döviz.com da yoktu
            //ve bu servisteki işlemleri tl baz alınarak yapıldı.
            if (baseDoviz == null) {
                baseDoviz = new DovizComCurrencyObject();
            }
            //Tüm döviz kurlarının anlık değerlerini çekiyorum.
            List<DovizComCurrencyObject> dovizComList = getDovizComCurrencyList();
            //Gönderilen kura göre tl karşılığını hesaplıyor
            double tlValue = val1 * baseDoviz.getBuying();// getTlValue(val1, baseDoviz.getBuying());
            CurrencyObject result = new CurrencyObject();
            result.setBase(base.toUpperCase());
            //Tüm listede dolaşarak diğer kurlardaki karşılığını hesaplıyor.ve listeye ekliyor.
            for (DovizComCurrencyObject row : dovizComList) {
                //Parametre olarak gönderilen kuru Rates listesine eklemiyoruz.
                if (!row.getCode().equals(baseDoviz.getCode())) {
                    result.getRates().add(new Rates(row.getCode(), tlValue * (1 / row.getBuying())));
                }
            }
            return result;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * Tüm döviz kurlarını liste olarak çekmek için
     *
     * @return
     */
    public List<DovizComCurrencyObject> getDovizComCurrencyList() {
        String result = getDovizComResponseString("all");
        if (result.equals("null") || result.equals("")) {
            return null;
        }
        Gson gson = new GsonBuilder().create();
        List<DovizComCurrencyObject> fromJson = gson.fromJson(result.toString(),
                new TypeToken<List<DovizComCurrencyObject>>() {
                }.getType());
        return fromJson;
    }

    /***
     * Tek bir döviz kodunu çekmek için kullanıldı.
     * @param code
     * @return
     */
    public DovizComCurrencyObject getDovizComCurrency(String code) {
        String result = getDovizComResponseString(code.toUpperCase());
        if (result.equals("null") || result.equals("")) {
            return null;
        }
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(result, DovizComCurrencyObject.class);
    }

    /**
     * doviz.com uzerinden  gönderdiğimiz koda göre kur verilerini çekmek için
     *
     * @param code Hangi döviz kodunun verileri cekilmek isteniyorsa o
     * @return
     */
    private String getDovizComResponseString(String code) {
        try {
            String url = dovizUrl + code + "/latest";
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            }
            return "";

        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
