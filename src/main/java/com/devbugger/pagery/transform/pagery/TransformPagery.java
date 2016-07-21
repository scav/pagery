package com.devbugger.pagery.transform.pagery;

public interface TransformPagery<T> {

    /**
     * Transform the content within a template based on the occurrence
     * of markers defined in {@link PageryMarkers}
     * @param input object
     * @return the transformed html as S
     */
    T transform(T input);

}