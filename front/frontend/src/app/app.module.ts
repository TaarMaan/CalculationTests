import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {routing} from './app.route';
import {FormsModule} from "@angular/forms";
import {InputsComponent} from "./shared/input/inputs.component";
import {RedblackgreenDirective} from './shared/directive/redblackgreen.directive';
import {RoundingPipe} from "./pipe/rounding.pipe";
import { AppService } from './service/app.component.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent, InputsComponent, RedblackgreenDirective, RoundingPipe
  ],
  imports: [
    BrowserModule,
    routing,
    FormsModule,
    HttpClientModule
  ],
  providers: [AppService],
  bootstrap: [AppComponent]
})

export class AppModule {
}
