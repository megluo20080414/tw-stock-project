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
  yearMonth = ''; // 格式: yyyy-MM
  result: any;
  error = '';

  constructor(private http: HttpClient) {}

  getPrice() {
    if (!this.stockNo.trim()) {
      this.error = '請輸入股票代碼';
      this.result = null;
      return;
    }

    const formattedDate = this.yearMonth ? this.yearMonth.replace('-', '') + '01' : '';

    const url = formattedDate
      ? `http://13.51.176.23:8080/api/stocks/${this.stockNo}?date=${formattedDate}`
      : `http://13.51.176.23:8080/api/stocks/${this.stockNo}`;

    console.log('✅ getPrice() 被呼叫了，股票代碼:', this.stockNo);
    console.log('✅ 查詢 URL:', url);

    this.http.get<any>(url).subscribe({
      next: (data) => {
        if (!data || !data.data || data.data.length === 0) {
          this.result = null;
          this.error = '查無資料';
          return;
        }

        this.result = data;
        this.error = '';
      },
      error: (err) => {
        console.error('❌ API 錯誤:', err);
        this.result = null;
        this.error = '查詢失敗，請稍後再試';
      }
    });
  }
}
