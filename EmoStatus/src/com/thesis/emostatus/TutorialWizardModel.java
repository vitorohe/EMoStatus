/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thesis.emostatus;

import wizard.model.*;

import android.content.Context;

public class TutorialWizardModel extends AbstractWizardModel {
    public TutorialWizardModel(Context context) {
        super(context);
    }

    @Override
    protected PageList onNewRootPageList() {
        return new PageList(
            new SingleViewPage(this, "Bienvenido",R.layout.tutorial_step1),
            new SingleViewPage(this, "Lista de usuarios",R.layout.tutorial_step2),
            new SingleViewPage(this, "Historial",R.layout.tutorial_step3),
            new SingleViewPage(this, "Monitoreo",R.layout.tutorial_step4),
            new SingleViewPage(this, "Alertas",R.layout.tutorial_step5)
        );
    }
}
