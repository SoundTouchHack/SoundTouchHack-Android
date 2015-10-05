package com.bose.mdietger.soundtouchandroid.http;

import android.util.Log;
import android.util.Xml;

import com.bose.mdietger.soundtouchandroid.http.volume.Volume;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * XmlMarshaller class. This singleton class is responsible for (un-)marshalling
 * the data Xml sent and received by de devicemanager.
 */
public class XmlMarshaller {

    private static final String TAG = "XmlMarshaller";

    private static XmlMarshaller marshaller;

    public static XmlMarshaller getInstance() {
        if (marshaller == null) {
            marshaller = new XmlMarshaller();
        }
        return marshaller;
    }

    /**
     * Unmarshalls an Xml to Java obj.
     * @param type the Class type
     * @param xml the xml to unmarshall
     * @param <T> the type
     * @return T the object
     */
    public <T> T unmarshall(Class<T> type, String xml) {
        try {
            Serializer serializer = new Persister();
            T t = serializer.read(type, new ByteArrayInputStream(xml.getBytes("UTF-8")));
            return t;
        } catch (Exception e) {
            Log.e(TAG, "error unmarshalling xml: " + e.getMessage());
            return null;
        }
    }

    /**
     * Marshalls an Java obj to Xml.
     * @param t the Java obj to marshall
     * @param <T> the type
     * @return String the xml
     */
    public <T> String marshall(T t) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            Serializer serializer = new Persister();
            serializer.write(t, bos);

            return bos.toString("UTF-8");
        } catch (Exception e) {
            Log.e(TAG, "error unmarshalling xml: " + e.getMessage());
            return null;
        }
    }

}
