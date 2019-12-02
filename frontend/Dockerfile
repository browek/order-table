FROM node:alpine as builder

RUN apk update && apk add yarn

WORKDIR '/app'

COPY ./package.json .
RUN yarn install

COPY . .
RUN yarn build

FROM nginx:alpine
EXPOSE 4200
COPY --from=builder /app/dist/frontend /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
