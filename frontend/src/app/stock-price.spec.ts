import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-stock-price',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './stock-price.html',
  styleUrls: ['./stock-price.css']
})
export class StockPriceComponent {
  stockNo = '';
  result: any;
  error = '';

  constructor(private http: HttpClient) {}

  getPrice() {
    if (!this.stockNo.trim()) {
      this.error = '請輸入股票代碼';
      this.result = null;
      return;
    }
 
    this.http.get(`http://13.51.176.23:8080/api/stocks/${this.stockNo}`)
    .subscribe({
      next: (data) => {
        console.log('API 回傳資料:', data); // <-- 看這裡是什麼
        this.result = data;
        this.error = '';
      },
      error: (err) => {
        console.error('API 發生錯誤:', err);
        this.result = null;
        this.error = '查詢失敗，請稍後再試';
      }
    });
  }
}
