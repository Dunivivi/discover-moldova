import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { AccountService } from '../../core/auth/account.service';
import { Account } from '../../core/auth/account.model';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { NgbCarousel, NgbSlideEvent, NgbSlideEventSource } from '@ng-bootstrap/ng-bootstrap';
import { LoginService } from '../../login/login.service';
import { Router } from '@angular/router';
import { LANGUAGES } from 'app/config/language.constants';
import { SessionStorageService } from 'ngx-webstorage';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'jhi-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit, OnDestroy {
  @ViewChild('carousel', { static: true }) carousel!: NgbCarousel;

  reviews = [
    {
      name: 'Abraham Lincoln',
      body: 'Best place i have ever been'
    }
  ];


  languages = LANGUAGES;
  currentLanguage: string = LANGUAGES[1];

  account: Account | null = null;

  paused = false;
  unpauseOnArrow = false;
  pauseOnIndicator = false;
  pauseOnHover = true;
  pauseOnFocus = true;

  private readonly destroy$ = new Subject<void>();

  constructor(private accountService: AccountService,
              private loginService: LoginService,
              private router: Router,
              private sessionStorageService: SessionStorageService,
              private translateService: TranslateService) {
    const localeSession = this.sessionStorageService.retrieve('locale');
    localeSession !== undefined && localeSession !== null
      ? this.currentLanguage = localeSession : this.currentLanguage = LANGUAGES[1];
  }

  ngOnInit(): void {
    this.accountService
      .getAuthenticationState()
      .pipe(takeUntil(this.destroy$))
      .subscribe(account => (this.account = account));
  };

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  togglePaused(): void {
    if (this.paused) {
      this.carousel.cycle();
    } else {
      this.carousel.pause();
    }
    this.paused = !this.paused;
  }

  onSlide(slideEvent: NgbSlideEvent): void {
    if (
      this.unpauseOnArrow &&
      slideEvent.paused &&
      (slideEvent.source === NgbSlideEventSource.ARROW_LEFT || slideEvent.source === NgbSlideEventSource.ARROW_RIGHT)
    ) {
      this.togglePaused();
    }
    if (this.pauseOnIndicator && !slideEvent.paused && slideEvent.source === NgbSlideEventSource.INDICATOR) {
      this.togglePaused();
    }
  }

  logout(): void {
    this.loginService.logout();
    this.router.navigate(['']);
  }

  changeLanguage(languageKey: string): void {
    this.sessionStorageService.store('locale', languageKey);
    this.translateService.use(languageKey);
    this.currentLanguage = languageKey;
  }
}
