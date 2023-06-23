import {Component} from '@angular/core';
import { AppService } from './service/app.component.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  constructor(private appService: AppService){};
  title: string = 'Калькулятор';

  num3: number = 2;
  operations = [
    "Сложить",
    "Вычесть",
    "Поделить",
    "Умножить"
  ];

  calc(calcData: CalcData) {
    switch (calcData.selectedValue) {
      case (this.operations)[0]: {
        this.appService.addition(calcData.num1, calcData.num2, calcData.system).subscribe(res => { this.num3 = res })
        break;
      }
      case (this.operations)[1]: {
        this.appService.subtraction(calcData.num1, calcData.num2, calcData.system).subscribe(res => { this.num3 = res })
        break;
      }
      case (this.operations)[2]: {
        this.appService.division(calcData.num1, calcData.num2, calcData.system).subscribe(res => { this.num3 = res })
        break;
      }
      case (this.operations)[3]: {
        this.appService.multiplication(calcData.num1, calcData.num2, calcData.system).subscribe(res => { this.num3 = res })
        break;
      }
    }
  }
}

export interface CalcData {
  num1: string;
  num2: string;
  selectedValue: string;
  system: string;
}
