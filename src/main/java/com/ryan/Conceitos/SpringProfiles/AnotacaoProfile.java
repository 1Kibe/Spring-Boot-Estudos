package com.ryan.Conceitos.SpringProfiles;

import org.springframework.context.annotation.Profile;

public class AnotacaoProfile {

    @Profile("dev")//so vai ser usado se a aplicasao estiver no profile DEV
    public void name() {
        
    }
}
