import { Component, signal } from '@angular/core';
import { StockPriceComponent } from './stock-price'; // ✅ 引入元件

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [StockPriceComponent], // ✅ 引入進來
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('stock-ui');
}
