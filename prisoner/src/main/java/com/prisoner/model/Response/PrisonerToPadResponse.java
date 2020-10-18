package com.prisoner.model.Response;

import com.prisoner.model.Prisoner;
import com.prisoner.model.PrisonerHeartBeat;
import com.prisoner.model.PrisonerRisk;

public class PrisonerToPadResponse {
    Prisoner prisoner;

    PrisonerHeartBeat prisonerHeartBeat;

    PrisonerRisk prisonerRisk;

    public Prisoner getPrisoner() {
        return prisoner;
    }

    public void setPrisoner(Prisoner prisoner) {
        this.prisoner = prisoner;
    }

    public PrisonerHeartBeat getPrisonerHeartBeat() {
        return prisonerHeartBeat;
    }

    public void setPrisonerHeartBeat(PrisonerHeartBeat prisonerHeartBeat) {
        this.prisonerHeartBeat = prisonerHeartBeat;
    }

    public PrisonerRisk getPrisonerRisk() {
        return prisonerRisk;
    }

    public void setPrisonerRisk(PrisonerRisk prisonerRisk) {
        this.prisonerRisk = prisonerRisk;
    }
}
