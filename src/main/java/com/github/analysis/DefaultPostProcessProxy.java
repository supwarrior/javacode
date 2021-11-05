package com.github.analysis;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter(AccessLevel.PROTECTED)
@Component("defaultPostProcessProxy")
public class DefaultPostProcessProxy<P, R> extends BasePostProcessProxy<P, R> {

}
