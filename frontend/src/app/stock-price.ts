import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-stock-price',
  standalone: true,
  imports: [CommonModule, HttpClientModule, FormsModule],
  templateUrl: './stock-price.html',
  styleUrls: ['./stock-price.css'],
})
export class StockPriceComponent {
  stockNo: string = '';
  result: any = null;
  error: string = '';

  constructor(private http: HttpClient) {}

  getPrice() {
    if (!this.stockNo) return;

    this.http.get(`http://localhost:8080/api/stocks/${this.stockNo}`)
      .subscribe({
        next: (data) => {
          this.result = data;
          this.error = '';
        },
        error: (err) => {
          this.error = '查詢失敗，請檢查股票代碼或後端是否開啟';
          this.result = null;
        }
      });
  }
}
