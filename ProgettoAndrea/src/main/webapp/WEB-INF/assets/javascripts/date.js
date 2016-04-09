// Funzioni aggiuntive per manipolare le Date
Date.prototype.equals = function(date) {
  return this.getTime() === date.getTime();
};
Date.prototype.clone = function() {
  return new Date(this);
};
Date.prototype.nthDate = function(n) {
  return this.clone().addDate(n);
};
Date.prototype.previousDate = function() {
  return this.nthDate(-1);
};
Date.prototype.nextDate = function() {
  return this.nthDate(1);
};
Date.prototype.addDate = function(n) {
  this.setDate(this.getDate()+n);
  return this;
};
Date.prototype.addMonth = function(n) {
  this.setMonth(this.getMonth()+n);
  return this;
};
Date.prototype.addYear = function(n) {
  this.setFullYear(this.getFullYear()+n);
  return this;
};
Date.prototype.addHours = function(n) {
  this.setHours(this.getHours()+n);
  return this;
};
Date.prototype.addMinutes = function(n) {
  this.setMinutes(this.getMinutes()+n);
  return this;
};
Date.prototype.addSeconds = function(n) {
  this.setSeconds(this.getSeconds()+n);
  return this;
};
Date.prototype.addMilliseconds = function(n) {
  this.setMilliseconds(this.getMilliseconds()+n);
  return this;
};
