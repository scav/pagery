package com.devbugger.pagery.transform.pagery;

public interface TransformPagery<T> {

    /**
     * Transform the content within a template based on the occurrence
     * of markers defined in {@link PageryMarkers}
     * @param input the raw html string file containing markers
     * @param data the data used to replace markers
     * @return the transformed html as a string
     */
    String transform(String input, T data);


}