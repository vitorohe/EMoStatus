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
            new SingleViewPage(this, "Bienvenido"),
            new SingleViewPage(this, "Lista de usuarios"),
            new SingleViewPage(this, "Historial"),
            new SingleViewPage(this, "Monitoreo"),
            new SingleViewPage(this, "Alertas")
        );
    }
}
